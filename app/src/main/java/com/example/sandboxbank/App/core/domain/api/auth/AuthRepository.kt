package com.example.sandboxbank.App.core.domain.api.auth

import com.example.sandboxbank.App.core.data.dto.login.LoginResponse
import com.example.sandboxbank.App.core.data.dto.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun registerUser(email: String, password: String): Flow<RegisterResponse>

    fun loginUser(email: String, password: String): Flow<LoginResponse>

}






