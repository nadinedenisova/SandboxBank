package com.example.sandboxbank.App.ui.debitcards.debit.di


import dagger.Module
import dagger.Provides

@Module
class CardsModule {

    @Provides
    fun provideUserIdProvider(): () -> Long = {
        123L
    }
}
