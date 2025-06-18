package com.example.sandboxbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sandboxbank.App.appComponent
import com.example.sandboxbank.cardmanager.cards.debit.intent.CardIntent
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.DebitCardScreen
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.CardViewModelFactory
import com.example.sandboxbank.cardmanager.cards.dto.CardRequest
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel

import javax.inject.Inject

class HostActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: CardViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)

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
