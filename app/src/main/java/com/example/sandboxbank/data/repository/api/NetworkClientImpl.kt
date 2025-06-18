package com.example.sandboxbank.data.repository.api

import com.example.sandboxbank.auth.data.dto.LoginRequest
import com.example.sandboxbank.auth.data.dto.RegisterRequest
import com.example.sandboxbank.auth.data.network.api.NetworkClient
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import javax.inject.Inject

class NetworkClientImpl @Inject constructor() : NetworkClient {
    override suspend fun register(registerRequest: RegisterRequest): ResultAuthState<String> {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginRequest: LoginRequest): ResultAuthState<String> {
        TODO("Not yet implemented")
    }

}