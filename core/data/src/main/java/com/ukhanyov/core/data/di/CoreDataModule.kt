package com.ukhanyov.core.data.di

import com.ukhanyov.core.data.auth.EncryptedSessionStorage
import com.ukhanyov.core.data.networking.HttpClientFactory
import com.ukhanyov.core.data.run.OfflineFirstRunRepository
import com.ukhanyov.core.domain.SessionStorage
import com.ukhanyov.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}