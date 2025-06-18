package com.example.sandboxbank.App.core.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.sandboxbank.App.core.di.annotations.ActivityContext
import com.example.sandboxbank.App.core.di.annotations.ActivityScope
import com.example.sandboxbank.App.core.di.annotations.AppContext
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.EncryptedPref
import com.example.sandboxbank.pinCode.AuthScreenUiStateMapper
import com.example.sandboxbank.pinCode.KeyStoreManager
import dagger.Module
import dagger.Provides

@Module
object PinCodeModule {

    @Provides
    fun provideMasterKey(@ActivityContext context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @EncryptedPref
    fun provideEncryptedSharedPreferences(
        @ActivityContext context: Context,
        masterKey: MasterKey
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "encrypted_pref",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    fun provideKeystoreManager(@EncryptedPref prefs: SharedPreferences): KeyStoreManager {
        return KeyStoreManager(prefs)
    }

    @Provides
    fun provideAuthScreenUiStateMapper(): AuthScreenUiStateMapper {
        return AuthScreenUiStateMapper()
    }
}