package com.example.sandboxbank.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.domain.api.auth.AuthIterator
import com.example.sandboxbank.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authIterator: AuthIterator
) : ViewModel() {
    private val _registerState = MutableStateFlow<Result<String>?>(null)
    val registerState: StateFlow<Result<String>?> = _registerState

    private val _loginState = MutableStateFlow<Result<String>?>(null)
    val loginState: StateFlow<Result<String>?> = _loginState

    private var failedAttempts = 0
    private val _isLoginButtonEnabled = MutableStateFlow(true)
    val isLoginButtonEnabled: StateFlow<Boolean> = _isLoginButtonEnabled

    fun login(email: String, password: String) {
        if (!_isLoginButtonEnabled.value) {
            _loginState.value = Result.Error("Попробуйте снова через 5 минут")
            return
        }

        viewModelScope.launch {
            _loginState.value = Result.Loading

            authIterator.loginUser(email, password)
                .catch { e ->
                    _loginState.value = Result.Error("Ошибка сети: ${e.message}")
                }
                .collect { result ->
                    when (result) {
                        is Result.Success<String> -> {
                            failedAttempts = 0
                            _loginState.value = Result.Success(result.data)
                        }
                        is Result.Error -> {
                            failedAttempts++
                            if (failedAttempts >= 5) {
                                _isLoginButtonEnabled.value = false
                                viewModelScope.launch {
                                    delay(5 * 60 * 1000) // 5 minutes timeout
                                    _isLoginButtonEnabled.value = true
                                    failedAttempts = 0
                                }
                            }
                            _loginState.value = Result.Error(result.message)
                        }
                        else -> {
                            _loginState.value = Result.Loading
                        }
                    }
                }
        }
    }
}