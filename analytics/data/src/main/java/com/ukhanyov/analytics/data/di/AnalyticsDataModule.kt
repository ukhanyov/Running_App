package com.ukhanyov.analytics.data.di

import com.ukhanyov.analytics.data.RoomAnalyticsRepository
import com.ukhanyov.analytics.domain.AnalyticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
}