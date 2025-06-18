package com.example.sandboxbank.di.components

import android.content.Context
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.CoreAppModule
import com.example.sandboxbank.di.annotations.AppContext
import com.example.sandboxbank.di.annotations.ApplicationScope
import com.example.sandboxbank.di.annotations.BaseUrl
import com.example.sandboxbank.di.annotations.PrefsKey
import com.example.sandboxbank.di.modules.PinCodeModule
import com.example.sandboxbank.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CoreAppModule::class,
    ]
)

interface AppComponent {
    fun inject(app: App)

    fun provideActivityComponent(): ActivityComponent.Builder

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance @AppContext context: Context,
            @BindsInstance @BaseUrl baseUrl: String,
            @BindsInstance @PrefsKey prefsKey: String,
        ): AppComponent
    }
}




