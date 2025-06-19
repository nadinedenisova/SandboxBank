package com.example.sandboxbank.profile.domain

import android.content.SharedPreferences
import javax.inject.Inject
import androidx.core.content.edit
import com.example.sandboxbank.App.core.di.annotations.EncryptedPref
import com.example.sandboxbank.profile.data.Language

class SettingStoreManager @Inject constructor(
    @EncryptedPref private val sharedPreferences: SharedPreferences
) {
    fun getTheme(): Boolean {
        return sharedPreferences.getBoolean("darkTheme", false)
    }

    fun setDarkTheme(set: Boolean) {
        sharedPreferences.edit { putBoolean("darkTheme", set) }
    }

    fun getLanguage(): Language {
        return when(sharedPreferences.getString("lang", "RUS").toString()){
            "RUS" -> Language.RUS
            else -> {
                Language.ENG
            }
        }
    }

    fun setLanguage(lang: Language){
        sharedPreferences.edit {putString("lang", lang.name)}
    }

}