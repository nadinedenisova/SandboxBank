package com.example.sandboxbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sandboxbank.mainscreen.ui.MainScreen
import com.example.sandboxbank.mainscreen.ui.SettingScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingScreen()
        }
    }
}

