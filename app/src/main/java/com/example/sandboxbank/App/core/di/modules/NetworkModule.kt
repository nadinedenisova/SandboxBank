package com.example.sandboxbank.App.core.di.modules

import com.example.sandboxbank.App.core.di.annotations.ActivityScope
import com.example.sandboxbank.auth.data.repository.api.NetworkClient
import com.example.sandboxbank.auth.data.repository.api.NetworkClientImpl
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {
    @ActivityScope
    @Provides
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }
}