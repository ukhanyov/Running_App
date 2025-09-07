package com.uknanyov.wear.app.presentation.di

import com.uknanyov.wear.app.presentation.RunningApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        (androidApplication() as RunningApp).applicationScope
    }
}