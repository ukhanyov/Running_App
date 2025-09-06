package com.ukhanyov.wear.run.presentation

sealed interface TrackerEvent {
    data object RunFinished: TrackerEvent
}