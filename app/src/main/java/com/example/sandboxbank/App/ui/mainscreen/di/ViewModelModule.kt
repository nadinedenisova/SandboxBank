package com.example.sandboxbank.App.ui.mainscreen.di

import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainScreenViewModel(): MainScreenViewModel {
        return MainScreenViewModel()
    }
}