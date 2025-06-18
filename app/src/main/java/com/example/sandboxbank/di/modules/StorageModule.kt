package com.example.sandboxbank.di.modules

import com.example.sandboxbank.data.storage.SecureStorage
import dagger.Module
import dagger.Provides

@Module
object StorageModule {
    @Provides
    fun provideSecureStorage(): SecureStorage {
        return SecureStorage()
    }
}