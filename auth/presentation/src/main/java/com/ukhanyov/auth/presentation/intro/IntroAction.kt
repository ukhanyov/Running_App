package com.ukhanyov.auth.presentation.intro

sealed interface IntroAction {
    data object OnSignInClicked: IntroAction
    data object OnSignUpClicked: IntroAction
}