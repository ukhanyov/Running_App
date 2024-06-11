package com.ukhanyov.core.data.di

import com.ukhanyov.core.data.auth.EncryptedSessionStorage
import com.ukhanyov.core.data.networking.HttpClientFactory
import com.ukhanyov.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}