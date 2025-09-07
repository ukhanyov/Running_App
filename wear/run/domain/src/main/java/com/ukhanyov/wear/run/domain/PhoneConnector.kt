package com.ukhanyov.wear.run.domain

import com.ukhanyov.core.connectivity.domain.DeviceNode
import com.ukhanyov.core.connectivity.domain.messaging.MessagingAction
import com.ukhanyov.core.connectivity.domain.messaging.MessagingError
import com.ukhanyov.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
    val messagingActions: Flow<MessagingAction>

    suspend fun sendActionToPhone(action: MessagingAction): EmptyResult<MessagingError>
}