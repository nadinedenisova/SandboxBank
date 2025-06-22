package com.example.sandboxbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelProvider
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.di.ViewModelFactory
import com.example.sandboxbank.App.ui.mainscreen.ui.MainScreen
import com.example.sandboxbank.pinCode.ui.PinCodeScreen
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
import com.example.sandboxbank.profile.ui.ProfileScreen
import javax.inject.Inject


class HostActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var profileScreenViewModel: ProfileScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.componentsContainer.createActivityComponent(this)
        App.componentsContainer.activityComponent.inject(this)

        setContent {
            LocalViewModelFactoryProvider(viewModelProviderFactory) {
                MainScreen()
            }
        }
    }
}

