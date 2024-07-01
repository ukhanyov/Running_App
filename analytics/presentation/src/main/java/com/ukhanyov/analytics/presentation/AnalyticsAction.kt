package com.ukhanyov.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick : AnalyticsAction
}