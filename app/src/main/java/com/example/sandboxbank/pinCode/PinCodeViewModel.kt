package com.example.sandboxbank.pinCode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.pinCode.ui.AuthScreenUiState
import com.example.sandboxbank.pinCode.ui.PinLockEvent
import com.example.sandboxbank.pinCode.ui.model.AuthScreenState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PinCodeViewModel @Inject constructor(
    private val keyStoreManager: KeyStoreManager,
    val authScreenUiStateMapper: AuthScreenUiStateMapper
) : ViewModel() {

    private val authScreenMutableStateFlow =
        MutableStateFlow(AuthScreenState(screenType = getInitialScreenType()))

    val authScreenStateFlow: StateFlow<AuthScreenState> = authScreenMutableStateFlow.asStateFlow()

    private fun getInitialScreenType(): AuthScreenState.ScreenType {
        //keyStoreManager.clearPin()
        val isPingSet = keyStoreManager.getPin()
        return if (isPingSet.isNullOrBlank()) AuthScreenState.ScreenType.SIGN_UP else AuthScreenState.ScreenType.SIGN_IN
    }

    fun onEvent(event: PinLockEvent) {
        when (event) {
            is PinLockEvent.AddDigit -> {
                authScreenMutableStateFlow.update {
                    if (it.pinCode.length <= 6) {
                        it.copy(pinCode = it.pinCode + event.digit)
                    } else it
                }
            }

            PinLockEvent.CheckPin -> {
                val state = authScreenMutableStateFlow.value
                val storedPin = keyStoreManager.getPin()

                when (state.screenType) {
                    AuthScreenState.ScreenType.SIGN_UP -> {
                        if (state.pinCode.length == 6) {
                            authScreenMutableStateFlow.update {
                                it.copy(
                                    confirmPin = state.pinCode,
                                    screenType = AuthScreenState.ScreenType.TRY_PIN
                                )
                            }
                            keyStoreManager.savePin(state.pinCode)
                        } else {
                            authScreenMutableStateFlow.update {
                                it.copy(
                                    screenType = AuthScreenState.ScreenType.INCORRECT_PIN
                                )
                            }
                        }
                    }

                    AuthScreenState.ScreenType.SIGN_IN -> {
                        if (state.pinCode == storedPin) {
                            authScreenMutableStateFlow.update {
                                it.copy(
                                    confirmPin = "",
                                    screenType = AuthScreenState.ScreenType.SUCCESS
                                )
                            }
                        } else {
                            authScreenMutableStateFlow.update {
                                it.copy(screenType = AuthScreenState.ScreenType.INCORRECT_PIN)
                            }
                        }
                    }

                    AuthScreenState.ScreenType.INCORRECT_PIN -> {
                        val next = if (keyStoreManager.getPin().isNullOrBlank()) {
                            AuthScreenState.ScreenType.SIGN_UP
                        } else {
                            AuthScreenState.ScreenType.SIGN_IN
                        }
                        authScreenMutableStateFlow.update {
                            it.copy(screenType = next)
                        }

                        onEvent(PinLockEvent.CheckPin)
                    }

                    AuthScreenState.ScreenType.TRY_PIN -> {
                        authScreenMutableStateFlow.update {
                            it.copy(
                                confirmPin = "",
                                screenType = if (state.pinCode == state.confirmPin) AuthScreenState.ScreenType.SUCCESS else AuthScreenState.ScreenType.INCORRECT_PIN
                            )
                        }
                    }

                    AuthScreenState.ScreenType.SUCCESS -> TODO()
                }
                clearInput()
            }

            PinLockEvent.DeleteDigit -> {
                authScreenMutableStateFlow.update { state ->
                    if (state.pinCode.isNotEmpty()) {
                        state.copy(pinCode = state.pinCode.dropLast(1))
                    } else {
                        state
                    }
                }
            }
        }
    }

    private fun clearInput() {
        authScreenMutableStateFlow.update { it.copy(pinCode = "") }
    }
}