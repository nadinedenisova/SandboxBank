package com.example.sandboxbank.data.repository.api

import com.example.sandboxbank.data.dto.login.LoginRequest
import com.example.sandboxbank.data.dto.login.LoginResponse
import com.example.sandboxbank.data.dto.register.RegisterRequest
import com.example.sandboxbank.data.dto.register.RegisterResponse
import javax.inject.Inject

class NetworkClientImpl @Inject constructor() : NetworkClient {
    override suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}