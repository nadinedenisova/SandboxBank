package com.example.sandboxbank.profile.domain

import android.content.SharedPreferences
import com.example.sandboxbank.App.core.di.annotations.PlainPref
import com.example.sandboxbank.profile.data.Language
import javax.inject.Inject

open class GetStoreManager@Inject constructor(
    @PlainPref private val sharedPreferences: SharedPreferences
){
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