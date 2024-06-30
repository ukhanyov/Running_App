package com.ukhanyov.runningapp

import android.app.Application
import com.ukhanyov.auth.data.di.authDataModule
import com.ukhanyov.auth.presentation.di.authViewModelModule
import com.ukhanyov.core.data.di.coreDataModule
import com.ukhanyov.core.database.di.databaseModule
import com.ukhanyov.run.data.di.runDataModule
import com.ukhanyov.run.location.di.locationModule
import com.ukhanyov.run.network.di.networkModule
import com.ukhanyov.run.presentation.di.runPresentationModule
import com.ukhanyov.runningapp.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class RunningApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RunningApp)
            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule,
            )
        }
    }

}