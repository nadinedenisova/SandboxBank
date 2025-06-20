package com.example.sandboxbank.auth.data.repository.auth

import com.example.sandboxbank.auth.data.dto.register.RegisterRequest
import com.example.sandboxbank.auth.data.repository.api.NetworkClient
import com.example.sandboxbank.auth.data.storage.SecureStorage
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import com.example.sandboxbank.auth.domain.model.ResultAuthState.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val networkClient: NetworkClient,
    private val secureStorage: SecureStorage
): AuthRepository {

    override fun registerUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        when (val response = networkClient.register(RegisterRequest(email, password))) {
            is Success<String> -> {
                secureStorage.saveToken(response.data)
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
        TODO()
    }
}
