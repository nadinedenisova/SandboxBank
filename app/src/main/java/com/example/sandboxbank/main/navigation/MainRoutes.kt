package com.example.sandboxbank.main.navigation

sealed class MainRoutes(val route: String) {
    object Auth : MainRoutes("auth")
    object Registration : MainRoutes("registration")
    object PinCode : MainRoutes("pin-code")
    object Main : MainRoutes("main")
}