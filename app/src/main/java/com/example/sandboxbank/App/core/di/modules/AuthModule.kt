package com.example.sandboxbank.App.core.di.modules

import com.example.sandboxbank.auth.data.repository.api.NetworkClient
import com.example.sandboxbank.auth.data.repository.auth.AuthRepositoryImpl
import com.example.sandboxbank.auth.data.storage.SecureStorage
import com.example.sandboxbank.auth.domain.api.AuthInteractor
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.auth.domain.impl.AuthInteractorImpl
import dagger.Module
import dagger.Provides

@Module
object AuthModule {
    @Provides
    fun provideAuthIterator(repository: AuthRepository): AuthInteractor {
        return AuthInteractorImpl(repository)
    }

    @Provides
    fun provideAuthRepository(
        networkClient: NetworkClient,
        secureStorage: SecureStorage
    ): AuthRepository {
        return AuthRepositoryImpl(networkClient, secureStorage)
    }
}