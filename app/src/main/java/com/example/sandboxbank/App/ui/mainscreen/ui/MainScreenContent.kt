package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.appComponent

@Composable
fun MainScreenContent() {
    val context = LocalContext.current.applicationContext
    val viewModel = remember {
        (context as App).appComponent.getMainScreenViewModel()
    }
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