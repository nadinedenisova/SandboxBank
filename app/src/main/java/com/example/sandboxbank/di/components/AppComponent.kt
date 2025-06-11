package com.example.sandboxbank.di.components

import android.content.Context
import android.content.SharedPreferences
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.CoreAppModule
import com.example.sandboxbank.di.annotations.ApplicationScope
import com.example.sandboxbank.di.annotations.BaseUrl
import com.example.sandboxbank.di.annotations.PrefsKey
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope
@Component(
    modules = [
        CoreAppModule::class
    ]
)

interface AppComponent {
    fun inject(app: App)

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance @BaseUrl baseUrl: String,
            @BindsInstance @PrefsKey prefsKey: String,
        ): AppComponent
    }
}




