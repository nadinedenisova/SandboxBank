package com.example.sandboxbank.domain.impl.auth

import com.example.sandboxbank.data.dto.register.mapToDomain
import com.example.sandboxbank.domain.api.auth.AuthIterator
import com.example.sandboxbank.domain.api.auth.AuthRepository
import com.example.sandboxbank.domain.model.ResultAuthState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthIteratorImpl@Inject constructor(
    private val repository: AuthRepository
): AuthIterator {
    override fun registerUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        repository.registerUser(email, password)
            .map { response -> response.mapToDomain() }
    }

    override fun loginUser(email: String, password: String): Flow<ResultAuthState<String>> = flow {
        TODO()
    }
}