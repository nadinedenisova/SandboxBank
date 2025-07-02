package com.example.sandboxbank.main.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sandboxbank.App.ui.mainscreen.ui.MainScreenContent
import com.example.sandboxbank.LocalViewModelFactoryProvider
import com.example.sandboxbank.auth.ui.screen.RegistrationScreen
import com.example.sandboxbank.pinCode.ui.PinCodeScreen
import com.example.sandboxbank.ui.auth.AuthScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(
        navController = navHostController,
        startDestination = MainRoutes.Auth.route
    ) {
        composable(MainRoutes.Auth.route) {
            LocalViewModelFactoryProvider(viewModelFactory) {
                AuthScreen(
                    // fix чтобы пройти дальше вне зависимости от результата авторизации
                    //onLoginSuccess = { navHostController.navigate("pin-code") },
                    onLoginSuccess = { navHostController.navigate(MainRoutes.Main.route) },
                    onNavigateToRegistration = { navHostController.navigate("registration") }
                )
            }
        }
        composable(MainRoutes.Registration.route) {
            LocalViewModelFactoryProvider(viewModelFactory) {
                RegistrationScreen(
                    onBackClick = { navHostController.popBackStack() },
                    onRegisterSuccess = { navHostController.navigate("pin-code") }
                )
            }
        }

        composable(MainRoutes.PinCode.route) {
            LocalViewModelFactoryProvider(viewModelFactory) {
                PinCodeScreen(onPinSuccess = {
                    navHostController.navigate("main") {
                        popUpTo("pin-code") { inclusive = true }
                    }
                })
            }
        }

        composable(MainRoutes.Main.route) {
            MainScreenContent(viewModelFactory)
        }
    }
}
