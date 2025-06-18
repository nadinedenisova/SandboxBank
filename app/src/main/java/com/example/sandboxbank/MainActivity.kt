package com.example.sandboxbank

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sandboxbank.App.App
import com.example.sandboxbank.di.ViewModelFactory
import com.example.sandboxbank.pinCode.ui.PinCodeScreen
import com.example.sandboxbank.ui.auth.AuthScreen
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.componentsContainer.createActivityComponent(this)
        App.componentsContainer.activityComponent.inject(this)

        setContent {
            LocalViewModelFactoryProvider(viewModelProviderFactory) {
                AuthScreen({}, {})
            }
        }
    }
}

