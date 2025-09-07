package com.ukhanyov.wear.run.data

import com.ukhanyov.core.connectivity.domain.DeviceNode
import com.ukhanyov.core.connectivity.domain.DeviceType
import com.ukhanyov.core.connectivity.domain.NodeDiscovery
import com.ukhanyov.wear.run.domain.PhoneConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WatchToPhoneConnector(
    nodeDiscovery: NodeDiscovery,
    applicationScope: CoroutineScope
) : PhoneConnector {

    private val _connectedNode = MutableStateFlow<DeviceNode?>(null)
    override val connectedNode = _connectedNode.asStateFlow()

    val messagingActions = nodeDiscovery
        .observeConnectedDevices(DeviceType.WATCH)
        .onEach { connectedNodes ->
            val node = connectedNodes.firstOrNull()
            if (node != null && node.isNearby) {
                _connectedNode.value = node
            }
        }
        .launchIn(applicationScope)
}