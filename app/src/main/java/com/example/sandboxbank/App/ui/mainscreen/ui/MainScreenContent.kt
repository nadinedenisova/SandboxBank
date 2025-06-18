package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.viewModel


@Composable
fun MainScreenContent() {
    val viewModel = viewModel<MainScreenViewModel>()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navHostController = navController,
            )
        }
    }

}