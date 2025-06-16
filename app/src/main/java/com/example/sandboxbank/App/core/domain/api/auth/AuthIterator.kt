package com.example.sandboxbank.App.core.domain.api.auth

import kotlinx.coroutines.flow.Flow

interface AuthIterator {

    fun registerUser(email: String, password: String): Flow<Result<String>>
    fun loginUser(email: String, password: String): Flow<Result<String>>

}