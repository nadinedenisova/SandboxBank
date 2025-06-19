package com.example.sandboxbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.di.ViewModelFactory
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.DebitCardScreen
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import com.example.sandboxbank.pinCode.ui.PinCodeScreen
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
        val viewModel = ViewModelProvider(this, viewModelFactory)[CardViewModel::class.java]
        setContent {
            val state = viewModel.state.collectAsStateWithLifecycle().value
            DebitCardScreen(
                state = state,
                onCreateCardClick = {
                    viewModel.handleIntent(CardIntent.CreateCard(dummyCardRequest()))
                },
                onBackClick = { finish() },
                onDismissSuccessDialog = {
                    viewModel.clearCardResult()
                },
                onClearError = {
                    viewModel.clearError()
                }
            )
        }

    }

    private fun dummyCardRequest() = CardRequest(
        userId = 123L,
        currentCardNumber = 13212312312313,
        requestNumber = 13212312312313,
    )
}

