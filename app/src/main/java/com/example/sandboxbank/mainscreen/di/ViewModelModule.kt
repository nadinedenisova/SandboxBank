package com.example.sandboxbank.mainscreen.di

import com.example.sandboxbank.mainscreen.domain.MainScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainScreenViewModel(): MainScreenViewModel {
        return MainScreenViewModel()
    }
}