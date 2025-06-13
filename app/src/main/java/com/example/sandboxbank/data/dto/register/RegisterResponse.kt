package com.example.sandboxbank.data.dto.register

import com.example.sandboxbank.domain.model.ResultState

sealed class RegisterResponse{
    data object  Loading : RegisterResponse()
    data class Success(val token: String) : RegisterResponse()
    data class Error(val message: String) : RegisterResponse()
}

fun RegisterResponse.mapToDomain(): ResultState<String> = when (this) {
    is RegisterResponse.Success -> ResultState.Success(token)
    is RegisterResponse.Error -> ResultState.Error(message)
    RegisterResponse.Loading -> ResultState.Loading
}