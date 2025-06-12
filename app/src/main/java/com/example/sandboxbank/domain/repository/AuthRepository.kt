package com.example.sandboxbank.domain.repository

import com.example.sandboxbank.data.auth.model.AuthResponse

interface AuthRepository {
    suspend fun register(email: String, password: String): AuthResponse
    suspend fun login(email: String, password: String): AuthResponse
}