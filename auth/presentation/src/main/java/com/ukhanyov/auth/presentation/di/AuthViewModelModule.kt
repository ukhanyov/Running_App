package com.ukhanyov.auth.presentation.di

import com.ukhanyov.auth.presentation.login.LoginViewModel
import com.ukhanyov.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}