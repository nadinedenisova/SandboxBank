package com.example.sandboxbank.data.repository.api

import com.example.sandboxbank.data.dto.login.LoginRequest
import com.example.sandboxbank.data.dto.register.RegisterRequest
import com.example.sandboxbank.data.dto.login.LoginResponse
import com.example.sandboxbank.data.dto.register.RegisterResponse

interface NetworkClient {
    suspend fun register(registerRequest: RegisterRequest): RegisterResponse
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}