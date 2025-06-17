package com.example.sandboxbank.auth.ui.viewmodel

import com.example.sandboxbank.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.auth.domain.api.AuthIteractor
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel (
    private val authIterator: AuthIteractor
) : ViewModel()
{
    private val _authState = MutableStateFlow<ResultAuthState<String>?>(null)
    val authState: StateFlow<ResultAuthState<String>?> = _authState

    private var failedAttempts = 0
    private val _isLoginButtonEnabled = MutableStateFlow(true)
    val isLoginButtonEnabled: StateFlow<Boolean> = _isLoginButtonEnabled

    fun register(email: String, password: String) {
        viewModelScope.launch {
            authIterator.registerUser(email, password)
                .onEach { result ->
                    _authState.value = result
                }
                .collect{ }
        }
    }

    fun login(email: String, password: String) {
        if (!_isLoginButtonEnabled.value) {
            _authState.value = ResultAuthState.Error(R.string.repeat_again.toString())
            return
        }

        viewModelScope.launch {
            _authState.value = ResultAuthState.Loading

            authIterator.loginUser(email, password)
                .catch { e ->
                    _authState.value = ResultAuthState.Error("Ошибка: ${e.message}")
                }
                .collect { result ->
                    when (result) {
                        is ResultAuthState.Success<String> -> {
                            failedAttempts = 0
                            _authState.value = ResultAuthState.Success(result.data)
                        }
                        is ResultAuthState.Error -> {
                            failedAttempts++
                            if (failedAttempts >= 5) {
                                _isLoginButtonEnabled.value = false
                                viewModelScope.launch {
                                    delay(5 * 60 * 1000) // 5 minutes timeout
                                    _isLoginButtonEnabled.value = true
                                    failedAttempts = 0
                                }
                            }
                            _authState.value = ResultAuthState.Error(result.message)
                        }
                        else -> {
                            _authState.value = ResultAuthState.Loading
                        }
                    }
                }
        }
    }
}