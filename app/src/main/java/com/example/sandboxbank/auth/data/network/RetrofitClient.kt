package com.example.sandboxbank.auth.data.network

import com.example.sandboxbank.auth.data.dto.LoginRequest
import com.example.sandboxbank.auth.data.dto.RegisterRequest
import com.example.sandboxbank.auth.data.network.api.NetworkClient
import com.example.sandboxbank.auth.domain.model.ResultAuthState

class RetrofitClient: NetworkClient {
    override suspend fun register(registerRequest: RegisterRequest): ResultAuthState<String> {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginRequest: LoginRequest): ResultAuthState<String> {
        TODO("Not yet implemented")
    }
}