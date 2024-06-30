package com.ukhanyov.run.data.di

import com.ukhanyov.core.domain.run.SyncRunScheduler
import com.ukhanyov.run.data.CreateRunWorker
import com.ukhanyov.run.data.DeleteRunWorker
import com.ukhanyov.run.data.FetchRunsWorker
import com.ukhanyov.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}