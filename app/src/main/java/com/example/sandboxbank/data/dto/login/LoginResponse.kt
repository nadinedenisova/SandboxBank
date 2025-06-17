package com.example.sandboxbank.data.dto.login

import com.example.sandboxbank.domain.model.ResultAuthState

sealed class LoginResponse {
    data object Loading : LoginResponse()
    data class Success(val token: String) : LoginResponse()
    data class Error(val message: String) : LoginResponse()
}

fun LoginResponse.mapToDomain(): ResultAuthState<String> = when (this) {
    is LoginResponse.Success -> ResultAuthState.Success(token)
    is LoginResponse.Error -> ResultAuthState.Error(message)
    LoginResponse.Loading -> ResultAuthState.Loading
}