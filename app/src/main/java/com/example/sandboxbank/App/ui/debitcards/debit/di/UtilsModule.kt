package com.example.sandboxbank.App.ui.debitcards.debit.di

import android.content.Context
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideInternetUtil(context: Context): InternetUtil {
        return InternetUtil(context)
    }
}
