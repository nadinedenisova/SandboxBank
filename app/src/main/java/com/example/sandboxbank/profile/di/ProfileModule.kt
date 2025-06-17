package com.example.sandboxbank.profile.di

import android.content.SharedPreferences
import com.example.sandboxbank.di.annotations.EncryptedPref
import com.example.sandboxbank.profile.domain.SettingStoreManager
import dagger.Module
import dagger.Provides

@Module
object ProfileModule {

    @Provides
    fun provideSettingStoreManager(@EncryptedPref prefs: SharedPreferences): SettingStoreManager {
        return SettingStoreManager(prefs)
    }

}