package com.example.sandboxbank.App.core.data

import com.example.sandboxbank.App.core.data.dto.login.LoginRequest
import com.example.sandboxbank.App.core.data.dto.login.LoginResponse
import com.example.sandboxbank.App.core.data.dto.register.RegisterRequest
import com.example.sandboxbank.App.core.data.dto.register.RegisterResponse
import com.example.sandboxbank.App.core.data.repository.api.NetworkClient

class RetrofitClient: NetworkClient {
    override suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}