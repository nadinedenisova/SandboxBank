package com.example.sandboxbank.data.dto.register

import com.example.sandboxbank.domain.model.ResultAuthState

sealed class RegisterResponse{
    data object  Loading : RegisterResponse()
    data class Success(val token: String) : RegisterResponse()
    data class Error(val message: String) : RegisterResponse()
}

fun RegisterResponse.mapToDomain(): ResultAuthState<String> = when (this) {
    is RegisterResponse.Success -> ResultAuthState.Success(token)
    is RegisterResponse.Error -> ResultAuthState.Error(message)
    RegisterResponse.Loading -> ResultAuthState.Loading
}