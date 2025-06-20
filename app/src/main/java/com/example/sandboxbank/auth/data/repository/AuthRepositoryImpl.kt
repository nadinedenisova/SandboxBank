package com.example.sandboxbank.auth.data.repository

import com.example.sandboxbank.auth.data.dto.RegisterRequest
import com.example.sandboxbank.auth.data.network.api.NetworkClient
import com.example.sandboxbank.auth.data.storage.SecureStorageToken
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import com.example.sandboxbank.auth.domain.model.ResultAuthState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val networkClient: NetworkClient,
    private val secureStorage: SecureStorageToken
): AuthRepository {

    override fun registerUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        when (val response = networkClient.register(RegisterRequest(email, password))) {
            is Success<String> -> {
                secureStorage.saveToken(response.data)
                emit(Success(response.data))
            }
            is ResultAuthState.Error -> {
                emit(ResultAuthState.Error(response.message))
            }
            is ResultAuthState.Loading -> {
                emit(ResultAuthState.Loading)
            }
        }
    }

    override fun loginUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        TODO()
    }
}
