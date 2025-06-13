package com.example.sandboxbank.data.dto.login

import com.example.sandboxbank.domain.model.ResultState

sealed class LoginResponse {
    data object Loading : LoginResponse()
    data class Success(val token: String) : LoginResponse()
    data class Error(val message: String) : LoginResponse()
}

fun LoginResponse.mapToDomain(): ResultState<String> = when (this) {
    is LoginResponse.Success -> ResultState.Success(token)
    is LoginResponse.Error -> ResultState.Error(message)
    LoginResponse.Loading -> ResultState.Loading
}