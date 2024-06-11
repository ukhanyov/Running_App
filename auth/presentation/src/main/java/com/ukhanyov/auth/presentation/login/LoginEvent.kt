package com.ukhanyov.auth.presentation.login

import com.ukhanyov.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class Error(val error: UiText) : LoginEvent
    data object LoginSuccess : LoginEvent
}