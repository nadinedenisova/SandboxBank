package com.example.sandboxbank.App.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.sandboxbank.App.core.di.annotations.ActivityContext
import com.example.sandboxbank.App.core.di.annotations.ActivityPref
import com.example.sandboxbank.App.core.di.annotations.EncryptedPref
import com.example.sandboxbank.App.core.di.annotations.PlainPref
import com.example.sandboxbank.profile.domain.SettingStoreManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ProfileModule {

    const val SETTING_FILE_NAME = "app_preferences"

    @Provides
    @ActivityPref
    fun provideSharedPreference(@ActivityContext context: Context): SharedPreferences{
        return context.getSharedPreferences(SETTING_FILE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @ActivityPref
    fun provideSettingStoreManager(@ActivityPref prefs: SharedPreferences): SettingStoreManager {
        return SettingStoreManager(prefs)
    }

}