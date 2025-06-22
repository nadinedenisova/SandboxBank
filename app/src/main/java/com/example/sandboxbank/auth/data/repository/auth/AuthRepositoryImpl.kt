package com.example.sandboxbank.auth.data.repository.auth

import com.example.sandboxbank.auth.data.dto.login.LoginRequest
import com.example.sandboxbank.auth.data.dto.register.RegisterRequest
import com.example.sandboxbank.auth.data.repository.api.NetworkClient
import com.example.sandboxbank.auth.data.storage.SecureStorageManager
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import com.example.sandboxbank.auth.domain.model.ResultAuthState.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val networkClient: NetworkClient,
    private val secureStorageManager: SecureStorageManager
): AuthRepository {

    override fun registerUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        when (val response = networkClient.register(RegisterRequest(email, password))) {
            is Success<String> -> {
                secureStorageManager.apply {
                    clearToken()
                    saveToken(response.data)
                }
                emit(Success(response.data))
            }
            is Error -> {
                emit(Error(response.message))
            }
            is Loading -> {
                emit(Loading)
            }
        }
    }

    override fun loginUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        when (val response = networkClient.login(LoginRequest(email, password))) {
            is Success<String> -> {
                secureStorageManager.apply {
                    clearToken()
                    saveToken(response.data)
                }
                emit(Success(response.data))
            }
            is Error -> {
                emit(Error(response.message))
            }
            is Loading -> {
                emit(Loading)
            }
        }
    }
}
