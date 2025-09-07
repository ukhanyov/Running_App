package com.ukhanyov.run.domain

import com.ukhanyov.core.connectivity.domain.DeviceNode
import kotlinx.coroutines.flow.StateFlow

interface WatchConnector {
    val connectedDevice: StateFlow<DeviceNode?>

    fun setIsTrackable(isTrackable: Boolean)
}