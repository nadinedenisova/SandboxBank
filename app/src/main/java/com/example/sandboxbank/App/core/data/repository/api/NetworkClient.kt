package com.example.sandboxbank.App.core.data.repository.api

import com.example.sandboxbank.App.core.data.dto.login.LoginRequest
import com.example.sandboxbank.App.core.data.dto.register.RegisterRequest
import com.example.sandboxbank.App.core.data.dto.login.LoginResponse
import com.example.sandboxbank.App.core.data.dto.register.RegisterResponse

interface NetworkClient {
    suspend fun register(registerRequest: RegisterRequest): RegisterResponse
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}