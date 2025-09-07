package com.ukhanyov.wear.run.domain

import com.ukhanyov.core.connectivity.domain.DeviceNode
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
}