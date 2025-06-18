package com.example.sandboxbank.di.modules

import com.example.sandboxbank.data.repository.api.NetworkClient
import com.example.sandboxbank.data.repository.auth.AuthRepositoryImpl
import com.example.sandboxbank.data.storage.SecureStorage
import com.example.sandboxbank.domain.api.auth.AuthIterator
import com.example.sandboxbank.domain.api.auth.AuthRepository
import com.example.sandboxbank.domain.impl.auth.AuthIteratorImpl
import dagger.Module
import dagger.Provides

@Module
object AuthModule {
    @Provides
    fun provideAuthIterator(repository: AuthRepository): AuthIterator {
        return AuthIteratorImpl(repository)  // Pass the repository dependency
    }

    @Provides
    fun provideAuthRepository(
        networkClient: NetworkClient,
        secureStorage: SecureStorage
    ): AuthRepository {
        return AuthRepositoryImpl(networkClient, secureStorage)
    }
}