package com.example.sandboxbank.App.core.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.sandboxbank.App.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}