package com.example.sandboxbank.di.components

import android.content.Context
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.CoreAppModule
import com.example.sandboxbank.di.annotations.ApplicationScope
import com.example.sandboxbank.di.annotations.BaseUrl
import com.example.sandboxbank.di.annotations.PrefsKey
import com.example.sandboxbank.mainscreen.di.ViewModelModule
import com.example.sandboxbank.mainscreen.domain.MainScreenViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CoreAppModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    fun inject(app: App)

    fun getMainScreenViewModel(): MainScreenViewModel

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance @BaseUrl baseUrl: String,
            @BindsInstance @PrefsKey prefsKey: String,
        ): AppComponent
    }
}




