package com.example.sandboxbank.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.sandboxbank.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}