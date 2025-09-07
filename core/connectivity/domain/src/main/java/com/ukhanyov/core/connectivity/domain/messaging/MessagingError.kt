package com.ukhanyov.core.connectivity.domain.messaging

import com.ukhanyov.core.domain.util.Error

enum class MessagingError : Error {
    CONNECTION_INTERRUPTED,
    DISCONNECTED,
    UNKNOWN
}