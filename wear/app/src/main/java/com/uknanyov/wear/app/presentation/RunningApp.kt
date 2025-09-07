package com.uknanyov.wear.app.presentation

import android.app.Application
import com.ukhanyov.core.connectivity.data.coreConnectivityDataModule
import com.ukhanyov.wear.run.data.di.wearRunDataModule
import com.ukhanyov.wear.run.presentation.di.wearRunPresentationModule
import com.uknanyov.wear.app.presentation.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RunningApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RunningApp)
            modules(
                appModule,
                wearRunPresentationModule,
                wearRunDataModule,
                coreConnectivityDataModule,
            )
        }
    }
}