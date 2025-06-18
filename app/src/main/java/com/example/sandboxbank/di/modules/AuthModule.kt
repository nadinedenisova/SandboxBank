package com.example.sandboxbank.di.modules

import com.example.sandboxbank.auth.data.network.api.NetworkClient
import com.example.sandboxbank.auth.data.repository.AuthRepositoryImpl
import com.example.sandboxbank.auth.data.storage.SecureStorage
import com.example.sandboxbank.auth.domain.api.AuthInteractor
import com.example.sandboxbank.auth.domain.api.AuthRepository
import com.example.sandboxbank.domain.impl.auth.AuthInteractorImpl
import dagger.Module
import dagger.Provides

@Module
object AuthModule {
    @Provides
    fun provideAuthIterator(repository: AuthRepository): AuthInteractor {
        return AuthInteractorImpl(repository)  // Pass the repository dependency
    }

    @Provides
    fun provideAuthRepository(
        networkClient: NetworkClient,
        secureStorage: SecureStorage
    ): AuthRepository {
        return AuthRepositoryImpl(networkClient, secureStorage)
    }
}