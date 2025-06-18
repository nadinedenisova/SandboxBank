package com.example.sandboxbank.auth.data.network.api

import com.example.sandboxbank.auth.data.dto.LoginRequest
import com.example.sandboxbank.auth.data.dto.RegisterRequest
import com.example.sandboxbank.auth.domain.model.ResultAuthState

interface NetworkClient {
    suspend fun register(registerRequest: RegisterRequest): ResultAuthState<String>
    suspend fun login(loginRequest: LoginRequest): ResultAuthState<String>
}