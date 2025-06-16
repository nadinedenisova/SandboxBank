package com.example.sandboxbank.App

import android.app.Application
import android.content.Context
import com.example.sandboxbank.App.core.di.components.AppComponent
import com.example.sandboxbank.di.components.DaggerAppComponent

private const val APP_PREFERENCES = "app_preferences"

class App: Application() {

    val component by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.factory()
            .create(
                context = this,
                baseUrl = "https://c210-87-253-62-46.ngrok-free.app",
                prefsKey = APP_PREFERENCES,
            )
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is App -> component
        else -> this.applicationContext.appComponent
    }