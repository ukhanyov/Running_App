package com.ukhanyov.auth.domain

import com.ukhanyov.core.domain.util.DataError
import com.ukhanyov.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}