package com.example.sandboxbank.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.di.ViewModelFactory
import com.example.sandboxbank.App.ui.mainscreen.ui.MainScreenContent
import com.example.sandboxbank.LocalViewModelFactoryProvider
import com.example.sandboxbank.auth.ui.screen.RegistrationScreen
import com.example.sandboxbank.main.navigation.MainNavGraph
import com.example.sandboxbank.ui.auth.AuthScreen
import javax.inject.Inject


class HostActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.componentsContainer.createActivityComponent(this)
        App.componentsContainer.activityComponent.inject(this)

        setContent {
            val navController = rememberNavController()

            MainNavGraph(
                navHostController = navController,
                viewModelFactory = viewModelFactory
            )
        }
    }
}

