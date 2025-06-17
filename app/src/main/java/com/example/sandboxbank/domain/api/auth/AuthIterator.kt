package com.example.sandboxbank.domain.api.auth

import com.example.sandboxbank.domain.model.ResultAuthState
import kotlinx.coroutines.flow.Flow

interface AuthIterator {

    fun registerUser(email: String, password: String): Flow<ResultAuthState<String>>
    fun loginUser(email: String, password: String): Flow<ResultAuthState<String>>

}