package com.example.sandboxbank.pinCode

import android.content.SharedPreferences
import com.example.sandboxbank.di.annotations.EncryptedPref
import javax.inject.Inject

class KeyStoreManager @Inject constructor(
    @EncryptedPref private val sharedPreferences: SharedPreferences
) {
    fun savePin(pin: String) {
        sharedPreferences.edit().putString("pin", pin).apply()
    }

    fun getPin(): String? {
        return sharedPreferences.getString("pin", null)
    }

    fun clearPin() {
        sharedPreferences.edit().remove("pin").apply()
    }
}