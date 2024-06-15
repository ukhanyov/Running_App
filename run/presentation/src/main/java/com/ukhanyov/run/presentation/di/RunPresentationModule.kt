package com.ukhanyov.run.presentation.di

import com.ukhanyov.run.domain.RunningTracker
import com.ukhanyov.run.presentation.active_run.ActiveRunViewModel
import com.ukhanyov.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}