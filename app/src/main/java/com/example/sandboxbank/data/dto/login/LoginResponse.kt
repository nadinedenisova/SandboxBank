package com.example.sandboxbank.data.dto.login

import com.example.sandboxbank.domain.model.Result

sealed class LoginResponse {
    data object Loading : LoginResponse()
    data class Success(val token: String) : LoginResponse()
    data class Error(val message: String) : LoginResponse()
}

fun LoginResponse.mapToDomain(): Result<String> = when (this) {
    is LoginResponse.Success -> Result.Success(token)
    is LoginResponse.Error -> Result.Error(message)
    LoginResponse.Loading -> Result.Loading
}