package com.example.sandboxbank.domain.api.auth

import com.example.sandboxbank.data.dto.login.LoginResponse
import com.example.sandboxbank.data.dto.register.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun registerUser(email: String, password: String): Flow<RegisterResponse>

    fun loginUser(email: String, password: String): Flow<LoginResponse>

}






