package com.example.sandboxbank.data.dto.register

import com.example.sandboxbank.domain.model.Result

sealed class RegisterResponse{
    data object  Loading : RegisterResponse()
    data class Success(val token: String) : RegisterResponse()
    data class Error(val message: String) : RegisterResponse()
}

fun RegisterResponse.mapToDomain(): Result<String> = when (this) {
    is RegisterResponse.Success -> Result.Success(token)
    is RegisterResponse.Error -> Result.Error(message)
    RegisterResponse.Loading -> Result.Loading
}