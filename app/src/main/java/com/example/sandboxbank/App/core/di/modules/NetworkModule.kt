package com.example.sandboxbank.App.core.di.modules

import com.example.sandboxbank.auth.data.repository.api.NetworkClient
import com.example.sandboxbank.auth.data.repository.api.NetworkClientImpl
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }
}