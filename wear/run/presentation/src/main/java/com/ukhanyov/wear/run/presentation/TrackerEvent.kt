package com.ukhanyov.wear.run.presentation

import com.ukhanyov.core.presentation.ui.UiText

sealed interface TrackerEvent {
    data object RunFinished : TrackerEvent
    data class Error(val message: UiText) : TrackerEvent
}