package com.example.sandboxbank.App.core.di.modules

import android.content.Context
import com.example.sandboxbank.App.core.di.annotations.ActivityContext
import com.example.sandboxbank.auth.data.storage.SecureStorageManager

import dagger.Module
import dagger.Provides


@Module
object StorageModule {
    @Provides
    fun provideSecureStorage(@ActivityContext context: Context): SecureStorageManager {
        return SecureStorageManager(context)
    }
}