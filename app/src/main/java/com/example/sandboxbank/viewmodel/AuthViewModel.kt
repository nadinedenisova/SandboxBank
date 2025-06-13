package com.example.sandboxbank.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.domain.api.auth.AuthIterator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel (
    private val authIterator: AuthIterator
) : ViewModel()
{
    private val _registerState = MutableStateFlow<Result<String>?>(null)
    val registerState: StateFlow<Result<String>?> = _registerState

    private val _loginState = MutableStateFlow<Result<String>?>(null)
    val loginState: StateFlow<Result<String>?> = _loginState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            authIterator.registerUser(email, password)
                .onEach { result ->
                    _registerState.value = result
                }
                .collect{ }
        }
    }

    fun login(email: String, password: String) {
        TODO()
    }
}