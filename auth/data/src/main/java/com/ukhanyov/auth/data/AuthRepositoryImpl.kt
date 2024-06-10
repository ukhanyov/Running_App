package com.ukhanyov.auth.data

import com.ukhanyov.auth.domain.AuthRepository
import com.ukhanyov.core.data.networking.post
import com.ukhanyov.core.domain.util.DataError
import com.ukhanyov.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
) : AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password,
            )
        )
    }
}