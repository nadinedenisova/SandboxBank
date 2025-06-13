package com.example.sandboxbank.data.repository.auth

import com.example.sandboxbank.data.dto.login.LoginRequest
import com.example.sandboxbank.data.dto.register.RegisterRequest
import com.example.sandboxbank.data.repository.api.NetworkClient
import com.example.sandboxbank.data.storage.SecureStorage
import com.example.sandboxbank.domain.api.auth.AuthRepository
import com.example.sandboxbank.data.dto.login.LoginResponse
import com.example.sandboxbank.data.dto.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val networkClient: NetworkClient,
    private val secureStorage: SecureStorage
): AuthRepository {

    override fun registerUser(email: String, password: String): Flow<RegisterResponse> = flow {
        when(val response = networkClient.register(RegisterRequest(email, password))) {
            is RegisterResponse.Success-> {
                val accessToken = response.token
                secureStorage.saveToken(accessToken)
                emit(RegisterResponse.Success(accessToken))
            }
            is RegisterResponse.Error -> {
                emit(RegisterResponse.Error(response.message))
            }
            is RegisterResponse.Loading ->  {
                emit(RegisterResponse.Loading)
            }
        }
    }

    override fun loginUser(email: String, password: String): Flow<LoginResponse> = flow {
        TODO()
    }
}
