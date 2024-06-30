package com.ukhanyov.run.network.di

import com.ukhanyov.core.domain.run.RemoteRunDataSource
import com.ukhanyov.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}