package com.ukhanyov.auth.presentation.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ukhanyov.core.presentation.designsystem.RunningAppTheme
import com.ukhanyov.core.presentation.designsystem.components.GradientBackground

@Composable
fun IntroScreenRoot(
    onSignUpClicked: () -> Unit,
    onSignInClicked: () -> Unit,
) {
    IntroScreen(onAction = { action ->
        when (action) {
            IntroAction.OnSignInClicked -> onSignInClicked()
            IntroAction.OnSignUpClicked -> onSignUpClicked()
        }
    })
}

@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit
) {
    GradientBackground {

    }
}

@Preview
@Composable
private fun IntroScreenPreview() {
    RunningAppTheme { IntroScreen(onAction = {}) }
}