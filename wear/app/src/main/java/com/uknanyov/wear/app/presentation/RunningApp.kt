package com.uknanyov.wear.app.presentation

import android.app.Application
import com.ukhanyov.wear.run.data.di.wearRunDataModule
import com.ukhanyov.wear.run.presentation.di.wearRunPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RunningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RunningApp)
            modules(
                wearRunPresentationModule,
                wearRunDataModule
            )
        }
    }
}