@file:OptIn(FlowPreview::class)

package com.ukhanyov.wear.run.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukhanyov.core.connectivity.domain.messaging.MessagingAction
import com.ukhanyov.core.domain.util.Result
import com.ukhanyov.core.notification.ActiveRunService
import com.ukhanyov.wear.run.domain.ExerciseTracker
import com.ukhanyov.wear.run.domain.PhoneConnector
import com.ukhanyov.wear.run.domain.RunningTracker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class TrackerViewModel(
    private val exerciseTracker: ExerciseTracker,
    private val phoneConnector: PhoneConnector,
    private val runningTracker: RunningTracker
) : ViewModel() {

    var state by mutableStateOf(
        TrackerState(
            hasStartedRunning = ActiveRunService.isServiceActive.value,
            isRunActive = ActiveRunService.isServiceActive.value && runningTracker.isTracking.value,
            isTrackable = ActiveRunService.isServiceActive.value
        )
    )
        private set

    private val hasBodySensorPermission = MutableStateFlow(false)

    private val isTracking = snapshotFlow {
        state.isRunActive && state.isTrackable && state.isConnectedPhoneNearby
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    private val eventChannel = Channel<TrackerEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        phoneConnector
            .connectedNode
            .filterNotNull()
            .onEach { connectedNode ->
                state = state.copy(
                    isConnectedPhoneNearby = connectedNode.isNearby
                )
            }
            .combine(isTracking) { _, isTracking ->
                if (!isTracking) {
                    phoneConnector.sendActionToPhone(MessagingAction.ConnectionRequest)
                }
            }
            .launchIn(viewModelScope)

        runningTracker
            .isTrackable
            .onEach { isTrackable ->
                state = state.copy(isTrackable = isTrackable)
            }
            .launchIn(viewModelScope)

        isTracking
            .onEach { isTracking ->
                val result = when {
                    isTracking && !state.hasStartedRunning -> {
                        exerciseTracker.startExercise()
                    }

                    isTracking && state.hasStartedRunning -> {
                        exerciseTracker.resumeExercise()
                    }

                    !isTracking && state.hasStartedRunning -> {
                        exerciseTracker.pauseExercise()
                    }

                    else -> Result.Success(Unit)
                }

                if (result is Result.Error) {
                    result.error.toUiText()?.let {
                        eventChannel.send(TrackerEvent.Error(it))
                    }
                }

                if (isTracking) {
                    state = state.copy(hasStartedRunning = true)
                }
                runningTracker.setIsTracking(isTracking)
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            val isHeartRateTrackingSupported = exerciseTracker.isHeartRateTrackingSupported()
            state = state.copy(canTrackHeartRate = isHeartRateTrackingSupported)
        }

        val isAmbientMode = snapshotFlow { state.isAmbientMode }

        isAmbientMode
            .flatMapLatest { isAmbientMode ->
                if (isAmbientMode) {
                    runningTracker
                        .heartRate
                        .sample(10.seconds)
                } else {
                    runningTracker.heartRate
                }
            }
            .onEach {
                state = state.copy(heartRate = it)
            }
            .launchIn(viewModelScope)

        isAmbientMode
            .flatMapLatest { isAmbientMode ->
                if (isAmbientMode) {
                    runningTracker
                        .elapsedTime
                        .sample(10.seconds)
                } else {
                    runningTracker.elapsedTime
                }
            }
            .onEach {
                state = state.copy(elapsedDuration = it)
            }
            .launchIn(viewModelScope)

        runningTracker
            .distanceMeters
            .onEach {
                state = state.copy(distanceMeters = it)
            }
            .launchIn(viewModelScope)

        listenToPhoneActions()
    }

    fun onAction(action: TrackerAction, triggeredOnPhone: Boolean = false) {
        if (!triggeredOnPhone) {
            sendActionToPhone(action)
        }
        when (action) {
            is TrackerAction.OnBodySensorPermissionResult -> {
                hasBodySensorPermission.value = action.isGranted
                if (action.isGranted) {
                    viewModelScope.launch {
                        val isHeartRateTrackingSupported =
                            exerciseTracker.isHeartRateTrackingSupported()
                        state = state.copy(
                            canTrackHeartRate = isHeartRateTrackingSupported
                        )
                    }
                }
            }

            TrackerAction.OnFinishRunClick -> {
                viewModelScope.launch {
                    exerciseTracker.stopExercise()
                    eventChannel.send(TrackerEvent.RunFinished)

                    state = state.copy(
                        elapsedDuration = Duration.ZERO,
                        distanceMeters = 0,
                        heartRate = 0,
                        hasStartedRunning = false,
                        isRunActive = false
                    )
                }
            }

            TrackerAction.OnToggleRunClick -> {
                if (state.isTrackable) {
                    state = state.copy(
                        isRunActive = !state.isRunActive
                    )
                }
            }

            is TrackerAction.OnEnterAmbientMode -> {
                state = state.copy(
                    isAmbientMode = true,
                    burnInProtectionRequired = action.burnInProtectionRequired
                )
            }

            TrackerAction.OnExitAmbientMode -> {
                state = state.copy(isAmbientMode = false)
            }
        }
    }

    private fun sendActionToPhone(action: TrackerAction) {
        viewModelScope.launch {
            val messagingAction = when (action) {
                is TrackerAction.OnFinishRunClick -> MessagingAction.Finish
                is TrackerAction.OnToggleRunClick -> {
                    if (state.isRunActive) {
                        MessagingAction.Pause
                    } else {
                        MessagingAction.StartOrResume
                    }
                }

                else -> null
            }

            messagingAction?.let {
                val result = phoneConnector.sendActionToPhone(it)
                if (result is Result.Error) {
                    println("Tracker error: ${result.error}")
                }
            }
        }
    }

    private fun listenToPhoneActions() {
        phoneConnector
            .messagingActions
            .onEach { action ->
                when (action) {
                    MessagingAction.Finish -> {
                        onAction(TrackerAction.OnFinishRunClick, triggeredOnPhone = true)
                    }

                    MessagingAction.Pause -> {
                        if (state.isTrackable) {
                            state = state.copy(isRunActive = false)
                        }
                    }

                    MessagingAction.StartOrResume -> {
                        if (state.isTrackable) {
                            state = state.copy(isRunActive = true)
                        }
                    }

                    MessagingAction.Trackable -> {
                        state = state.copy(isTrackable = true)
                    }

                    MessagingAction.Untrackable -> {
                        state = state.copy(isTrackable = false)
                    }

                    else -> Unit
                }
            }
            .launchIn(viewModelScope)
    }
}