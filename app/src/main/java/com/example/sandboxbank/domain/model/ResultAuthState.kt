package com.example.sandboxbank.domain.model

sealed class ResultAuthState<out T> {
    data class Success<out T>(val data: T) : ResultAuthState<T>()
    data class Error(val message: String) : ResultAuthState<Nothing>()
    data object Loading : ResultAuthState<Nothing>()
}