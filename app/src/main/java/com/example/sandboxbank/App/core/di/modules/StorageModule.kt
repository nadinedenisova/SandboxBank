package com.example.sandboxbank.App.core.di.modules

import android.content.SharedPreferences
import com.example.sandboxbank.auth.data.storage.SecureStorage
import com.example.sandboxbank.profile.domain.GetStoreManager
import com.example.sandboxbank.profile.domain.SettingStoreManager
import dagger.Module
import dagger.Provides


@Module
object StorageModule {
    @Provides
    fun provideSecureStorage(): SecureStorage {
        return SecureStorage()
    }


}