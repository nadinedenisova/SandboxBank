package com.example.sandboxbank.presentation.auth

object AuthContract {
    data class State(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val error: String? = null,
        val isLoginButtonEnabled: Boolean = true,
        val remainingTimeout: Long = 0
    )

    sealed class Event {
        data class EmailChanged(val email: String) : Event()
        data class PasswordChanged(val password: String) : Event()
        object LoginClicked : Event()
        object RegisterClicked : Event()
    }

    sealed class Effect {
        data class ShowError(val message: String) : Effect()
        object NavigateToMain : Effect()
        object NavigateToRegistration : Effect()
        object NavigateToPinSetup : Effect()
    }
}