package com.ukhanyov.runningapp

import android.app.Application
import com.ukhanyov.auth.data.di.authDataModule
import com.ukhanyov.auth.presentation.di.authViewModelModule
import com.ukhanyov.core.data.di.coreDataModule
import com.ukhanyov.run.presentation.di.runViewModelModule
import com.ukhanyov.runningapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunningApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RunningApp)
            modules(
                authDataModule,
                authViewModelModule,
                runViewModelModule,
                appModule,
                coreDataModule,
            )
        }
    }

}