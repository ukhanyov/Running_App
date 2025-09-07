package com.ukhanyov.core.connectivity.data.di

import com.ukhanyov.core.connectivity.data.WearNodeDiscovery
import com.ukhanyov.core.connectivity.data.messaging.WearMessagingClient
import com.ukhanyov.core.connectivity.domain.NodeDiscovery
import com.ukhanyov.core.connectivity.domain.messaging.MessagingClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreConnectivityDataModule = module {
    singleOf(::WearMessagingClient).bind<MessagingClient>()
    singleOf(::WearNodeDiscovery).bind<NodeDiscovery>()
}