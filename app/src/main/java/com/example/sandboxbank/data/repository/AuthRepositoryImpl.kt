package com.example.sandboxbank.data.repository

import com.example.sandboxbank.data.auth.AuthDataSource
import com.example.sandboxbank.data.auth.model.AuthResponse
import com.example.sandboxbank.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun register(email: String, password: String): AuthResponse {
        return authDataSource.register(email, password)
    }

    override suspend fun login(email: String, password: String): AuthResponse {
        return authDataSource.login(email, password)
    }
}