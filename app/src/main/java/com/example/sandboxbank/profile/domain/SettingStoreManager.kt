package com.example.sandboxbank.profile.domain

import android.content.SharedPreferences
import javax.inject.Inject
import androidx.core.content.edit
import com.example.sandboxbank.App.core.di.annotations.ActivityPref
import com.example.sandboxbank.App.core.di.annotations.EncryptedPref
import com.example.sandboxbank.profile.data.Language

class SettingStoreManager @Inject constructor(
    @ActivityPref private val sharedPreferences: SharedPreferences
){

    fun setDarkTheme(set: Boolean) {
        sharedPreferences.edit { putBoolean("darkTheme", set) }
    }


    fun setLanguage(lang: Language){
        sharedPreferences.edit {putString("lang", lang.name)}
    }

    fun getTheme(): Boolean {
        return sharedPreferences.getBoolean("darkTheme", false)
    }

    fun getLanguage(): Language {
        return when(sharedPreferences.getString("lang", "RUS").toString()){
            "RUS" -> Language.RUS
            else -> {
                Language.ENG
            }
        }
    }

}