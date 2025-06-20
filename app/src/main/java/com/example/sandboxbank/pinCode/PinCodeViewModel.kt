package com.example.sandboxbank.pinCode

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.pinCode.ui.PIN_CODE_CORRECT_SIZE
import com.example.sandboxbank.pinCode.ui.intent.PinCodeIntent
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

    fun onEvent(event: PinCodeIntent) {
        when (event) {
            is PinCodeIntent.AddDigit -> addDigit(event.digit)
            PinCodeIntent.DeleteDigit -> deleteDigit()
        }
    }

    private fun handleCheckPin() {
        val state = authScreenMutableStateFlow.value
        when (state.screenType) {
            AuthScreenState.ScreenType.SIGN_UP -> handleSignUp(state)
            AuthScreenState.ScreenType.SIGN_IN -> handleSignIn(state)
            AuthScreenState.ScreenType.INCORRECT_PIN -> handleIncorrectPinRedirect()
            AuthScreenState.ScreenType.TRY_PIN -> handleTryPin(state)
            AuthScreenState.ScreenType.SUCCESS -> Unit
        }
    }

    private fun handleSignUp(state: AuthScreenState) {
        if (state.pinCode.length == PIN_CODE_CORRECT_SIZE) {
            keyStoreManager.savePin(state.pinCode)
            authScreenMutableStateFlow.update {
                it.copy(
                    confirmPin = state.pinCode,
                    screenType = AuthScreenState.ScreenType.TRY_PIN
                )
            }
        } else {
            authScreenMutableStateFlow.update {
                it.copy(screenType = AuthScreenState.ScreenType.INCORRECT_PIN)
            }
        }
    }

    private fun handleSignIn(state: AuthScreenState) {
        val storedPin = keyStoreManager.getPin()
        val isCorrect = state.pinCode == storedPin
        authScreenMutableStateFlow.update {
            it.copy(
                confirmPin = "",
                hasError = !isCorrect,
                screenType = if (isCorrect) AuthScreenState.ScreenType.SUCCESS else AuthScreenState.ScreenType.INCORRECT_PIN
            )
        }
    }

    private fun handleIncorrectPinRedirect() {
        val nextScreen = if (keyStoreManager.getPin().isNullOrBlank()) {
            AuthScreenState.ScreenType.SIGN_UP
        } else {
            AuthScreenState.ScreenType.SIGN_IN
        }

        authScreenMutableStateFlow.update {
            it.copy(screenType = nextScreen)
        }

        handleCheckPin()
    }

    private fun handleTryPin(state: AuthScreenState) {
        val isConfirmed = state.pinCode == state.confirmPin
        authScreenMutableStateFlow.update {
            it.copy(
                confirmPin = "",
                screenType = if (isConfirmed) AuthScreenState.ScreenType.SUCCESS else AuthScreenState.ScreenType.INCORRECT_PIN,
            )
        }
    }

    private fun addDigit(digit: String) {
        var shouldCheck = false

        authScreenMutableStateFlow.update { current ->
            if (current.pinCode.length < PIN_CODE_CORRECT_SIZE) {
                val updated = current.copy(pinCode = current.pinCode + digit, hasError = false)
                shouldCheck = updated.pinCode.length == PIN_CODE_CORRECT_SIZE
                updated
            } else current
        }

        if (shouldCheck) {
            handleCheckPin()
        }
    }

    private fun deleteDigit() {
        authScreenMutableStateFlow.update { state ->
            if (state.pinCode.isNotEmpty()) {
                state.copy(pinCode = state.pinCode.dropLast(1), hasError = false)
            } else {
                state
            }
        }
    }
}