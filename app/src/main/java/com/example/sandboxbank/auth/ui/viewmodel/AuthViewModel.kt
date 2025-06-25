package com.example.sandboxbank.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.auth.domain.api.AuthInteractor
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor (
    private val authInteractor: AuthInteractor
) : ViewModel()
{
    private val _authState = MutableStateFlow<ResultAuthState<Unit>?>(null)
    val authState: StateFlow<ResultAuthState<Unit>?> = _authState

    private var failedAttempts = 0
    private val _isLoginButtonEnabled = MutableStateFlow(true)
    val isLoginButtonEnabled: StateFlow<Boolean> = _isLoginButtonEnabled

    fun register(email: String, password: String) {
        viewModelScope.launch {
            authInteractor.registerUser(email, password)
                .onEach { result ->
                    _authState.value = result
                }
                .collect{ }
        }
    }

    fun login(email: String, password: String) {
        if (!_isLoginButtonEnabled.value) {
            _authState.value = ResultAuthState.Error(Unit.toString())
            return
        }

        viewModelScope.launch {
            _authState.value = ResultAuthState.Loading

            authInteractor.loginUser(email, password)
                .catch { e ->
                    _authState.value = ResultAuthState.Error(Unit.toString())
                }
                .collect { result ->
                    when (result) {
                        is ResultAuthState.Success -> {
                            failedAttempts = 0
                            _authState.value = ResultAuthState.Success(Unit)
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
                            _authState.value = ResultAuthState.Error(Unit.toString())
                        }
                        else -> {
                            _authState.value = ResultAuthState.Loading
                        }
                    }
                }
        }
    }
}