package com.example.sandboxbank.auth.domain.api


import com.example.sandboxbank.auth.domain.model.ResultAuthState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun registerUser(email: String, password: String): Flow<ResultAuthState<Unit>>
    fun loginUser(email: String, password: String): Flow<ResultAuthState<Unit>>

}






