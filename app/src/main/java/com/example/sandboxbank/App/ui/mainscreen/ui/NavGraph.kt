package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sandboxbank.App.ui.applycredit.ui.ApplyCredit
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.App.ui.financialScreen.ui.FinancialScreenContent
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.App.ui.mainscreen.data.Routes
import com.example.sandboxbank.credit.ui.CreditScreen
import com.example.sandboxbank.deposit.ui.DepositScreen
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
import com.example.sandboxbank.profile.ui.ProfileScreen
import com.example.sandboxbank.transaction.domain.TransactionViewModel
import com.example.sandboxbank.transaction.ui.TransactionScreen
import java.math.BigDecimal

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    startDestination: String = Routes.Cards.route // Используем route вместо name
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Routes.Cards.name) {
            CardsScreen()
        }
        composable(route = Routes.Finance.name) {
            val financialViewModel: FinancialScreenViewModel = viewModel(
                factory = viewModelFactory
            )
            FinancialScreenContent(
                financialViewModel,
                onDepositClick = { depositId ->
                    navHostController.navigate(Routes.depositDetails(depositId))
                },
                onCreditClick = { creditId ->
                    navHostController.navigate(Routes.creditDetails(creditId))
                },
                onApplyCreditClick = {
                    navHostController.navigate(Routes.ApplyCredit.route)
                }
            )
        }
        composable(route = Routes.History.name) {
            HistoryScreen()
        }
        composable(route = Routes.Transaction.name) {
            val transactionViewModel: TransactionViewModel = viewModel(
                factory = viewModelFactory
            )
            TransactionScreen(
                viewModel = transactionViewModel,
                { navHostController.popBackStack() })
        }
        composable(
            route = Routes.Deposit.route,
            arguments = listOf(navArgument("depositId") { type = NavType.LongType })
        ) { backStackEntry ->
            val depositId = backStackEntry.arguments?.getLong("depositId")
            val viewModel: FinancialItemDetailsViewModel = viewModel(
                factory = viewModelFactory
            )
            DepositScreen(
                viewModel = viewModel,
                depositId = depositId!!, //аргумент объявлен как обязательный, null сюда не прилетит
                navController = navHostController,
            )
        }

        composable(
            route = Routes.Credit.route,
            arguments = listOf(navArgument("creditId") { type = NavType.LongType })
        ) { backStackEntry ->
            val creditId = backStackEntry.arguments?.getLong("creditId")
            val viewModel: FinancialItemDetailsViewModel = viewModel(
                factory = viewModelFactory
            )
            CreditScreen(
                viewModel = viewModel,
                creditId = creditId!!, //аргумент объявлен как обязательный, null сюда не прилетит
                navController = navHostController,
            )
        }

        composable(route = Routes.ApplyCredit.name){
            ApplyCredit(
                onBackClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Routes.Profile.route){
            val profileScreenViewModel: ProfileScreenViewModel = viewModel( factory = viewModelFactory)
            ProfileScreen(profileScreenViewModel = profileScreenViewModel)
        }
    }
}


@Composable
fun CardsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Карт")
    }
}

@Composable
fun TransfersScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран Переводы")
    }
}

@Composable
fun HistoryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Экран История")
    }
}
