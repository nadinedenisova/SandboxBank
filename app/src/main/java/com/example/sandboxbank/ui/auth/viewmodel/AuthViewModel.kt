package com.example.sandboxbank.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.domain.api.auth.AuthIterator
import com.example.sandboxbank.domain.model.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel (
    private val authIterator: AuthIterator
) : ViewModel()
{
    private val _registerState = MutableStateFlow<Result<String>?>(null)
    val registerState: StateFlow<Result<String>?> = _registerState

    private val _loginState = MutableStateFlow<ResultState<String>?>(null)
    val loginState: StateFlow<ResultState<String>?> = _loginState

    private var failedAttempts = 0
    private val _isLoginButtonEnabled = MutableStateFlow(true)
    val isLoginButtonEnabled: StateFlow<Boolean> = _isLoginButtonEnabled

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
        if (!_isLoginButtonEnabled.value) {
            _loginState.value = ResultState.Error("Попробуйте снова через 5 минут")
            return
        }

        viewModelScope.launch {
            _loginState.value = ResultState.Loading

            authIterator.loginUser(email, password)
                .catch { e ->
                    _loginState.value = ResultState.Error("Ошибка сети: ${e.message}")
                }
                .collect { result ->
                    when (result) {
                        is ResultState.Success<String> -> {
                            failedAttempts = 0
                            _loginState.value = ResultState.Success(result.data)
                        }
                        is ResultState.Error -> {
                            failedAttempts++
                            if (failedAttempts >= 5) {
                                _isLoginButtonEnabled.value = false
                                viewModelScope.launch {
                                    delay(5 * 60 * 1000) // 5 minutes timeout
                                    _isLoginButtonEnabled.value = true
                                    failedAttempts = 0
                                }
                            }
                            _loginState.value = ResultState.Error(result.message)
                        }
                        else -> {
                            _loginState.value = ResultState.Loading
                        }
                    }
                }
        }
    }
}