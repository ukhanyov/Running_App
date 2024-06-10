package com.ukhanyov.auth.data.di

import com.ukhanyov.auth.data.AuthRepositoryImpl
import com.ukhanyov.auth.data.EmailPatternValidator
import com.ukhanyov.auth.domain.AuthRepository
import com.ukhanyov.auth.domain.PatternValidator
import com.ukhanyov.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}