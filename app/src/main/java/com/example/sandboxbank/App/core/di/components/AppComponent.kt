package com.example.sandboxbank.App.core.di.components

import android.content.Context
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.CoreAppModule
import com.example.sandboxbank.App.core.di.annotations.AppContext
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.BaseUrl
import com.example.sandboxbank.App.core.di.annotations.PrefsKey
import com.example.sandboxbank.App.core.di.modules.PinCodeModule
import com.example.sandboxbank.App.core.di.modules.ProfileModule
import com.example.sandboxbank.App.core.di.modules.ViewModelModule
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




