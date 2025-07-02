package com.example.sandboxbank.main.ui

import android.content.IntentFilter
import android.net.ConnectivityManager
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
import com.example.sandboxbank.notifications.AppNotifications
import com.example.sandboxbank.notifications.NetworkChangeReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class HostActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.componentsContainer.createActivityComponent(this)
        App.componentsContainer.activityComponent.inject(this)

        AppNotifications.checkRights(
            context = this,
            this@HostActivity
        )

        AppNotifications.createNotificationChannel(
            context = this
        )

        networkChangeReceiver = NetworkChangeReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            AppNotifications.showMockNotification(this@HostActivity)
        }

//        setContent {
//            MainScreenContent(
//                viewModelFactory = viewModelFactory
//            )
//        }
        setContent {
            val navController = rememberNavController()

            MainNavGraph(
                navHostController = navController,
                viewModelFactory = viewModelFactory
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }
}

