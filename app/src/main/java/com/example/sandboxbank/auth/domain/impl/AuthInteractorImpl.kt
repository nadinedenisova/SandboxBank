package com.example.sandboxbank.auth.domain.impl


import com.example.sandboxbank.auth.domain.api.AuthInteractor
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthInteractorImpl@Inject constructor(
    private val repository: AuthRepository
): AuthInteractor {
    override fun registerUser(email: String, password: String): Flow<ResultAuthState<Unit>> = flow {
        repository.registerUser(email, password)
    }

    override fun loginUser(email: String, password: String): Flow<ResultAuthState<Unit>> = flow {
        repository.loginUser(email, password)
    }
}