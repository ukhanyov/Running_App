package com.ukhanyov.run.data.di

import com.ukhanyov.run.data.CreateRunWorker
import com.ukhanyov.run.data.DeleteRunWorker
import com.ukhanyov.run.data.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)
}