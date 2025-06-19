package com.example.sandboxbank.App.core.di.modules

import com.example.sandboxbank.auth.data.storage.SecureStorage
import dagger.Module
import dagger.Provides


@Module
object StorageModule {
    @Provides
    fun provideSecureStorage(): SecureStorage {
        return SecureStorage()
    }
}