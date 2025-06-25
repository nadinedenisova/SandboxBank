package com.example.sandboxbank.auth.data.repository.api

import com.example.sandboxbank.auth.data.dto.login.LoginRequest
import com.example.sandboxbank.auth.data.dto.register.RegisterRequest
import com.example.sandboxbank.auth.domain.model.ResultAuthState

interface NetworkClient {
    suspend fun register(registerRequest: RegisterRequest): ResultAuthState<String>
    suspend fun login(loginRequest: LoginRequest): ResultAuthState<String>
}