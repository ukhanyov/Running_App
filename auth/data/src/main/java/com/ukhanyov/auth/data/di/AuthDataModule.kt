package com.ukhanyov.auth.data.di

import com.ukhanyov.auth.data.EmailPatternValidator
import com.ukhanyov.auth.domain.PatternValidator
import com.ukhanyov.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
}