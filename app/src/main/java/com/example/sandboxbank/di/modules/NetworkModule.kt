package com.example.sandboxbank.di.modules

import com.example.sandboxbank.auth.data.network.api.NetworkClient
import com.example.sandboxbank.data.repository.api.NetworkClientImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    fun provideNetworkClient(): NetworkClient {
        return NetworkClientImpl()
    }
}