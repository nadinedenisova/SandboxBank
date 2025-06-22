package com.example.sandboxbank.auth.data.repository.api

import com.example.sandboxbank.auth.data.dto.login.LoginRequest
import com.example.sandboxbank.auth.data.dto.register.RegisterRequest
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