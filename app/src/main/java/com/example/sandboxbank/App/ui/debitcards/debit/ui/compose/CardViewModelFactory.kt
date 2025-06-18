package com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sandboxbank.App.ui.debitcards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel
import javax.inject.Inject

import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil

class CardViewModelFactory @Inject constructor(
    private val remoteRepository: RemoteCardRepository,
    private val cardRepository: CardRepository,
    private val userIdProvider: () -> Long,
    private val internetUtil: InternetUtil
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            return CardViewModel(
                remoteRepository,
                cardRepository,
                userIdProvider,
                internetUtil
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class Cards View CardViewModelFactory ")
    }
}
