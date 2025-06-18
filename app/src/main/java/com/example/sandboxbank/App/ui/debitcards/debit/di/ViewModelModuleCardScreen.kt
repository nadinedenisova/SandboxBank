package com.example.sandboxbank.App.ui.debitcards.debit.di

import com.example.sandboxbank.App.ui.debitcards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.CardViewModelFactory
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModuleCardScreen {

    @Provides
    fun provideUserIdProvider(): () -> Long {
        //TODO Заменить на UserId когда будет аунтификация
        return { 123L }
    }

    @Provides
    fun provideCardViewModelFactory(
        remoteRepository: RemoteCardRepository,
        cardRepository: CardRepository,
        userIdProvider: () -> Long,
        internetUtil: InternetUtil,
    ): CardViewModelFactory {
        return CardViewModelFactory(remoteRepository, cardRepository, userIdProvider, internetUtil)
    }
}
