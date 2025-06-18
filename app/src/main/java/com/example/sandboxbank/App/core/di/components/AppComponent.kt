package com.example.sandboxbank.App.core.di.components

import android.content.Context
import com.example.sandboxbank.App.App
import com.example.sandboxbank.App.core.CoreAppModule
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.BaseUrl
import com.example.sandboxbank.App.core.di.annotations.PrefsKey
import com.example.sandboxbank.App.ui.debitcards.debit.di.ViewModelModuleCardScreen
import com.example.sandboxbank.App.ui.mainscreen.di.ViewModelModule
import com.example.sandboxbank.HostActivity
import com.example.sandboxbank.App.ui.mainscreen.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CoreAppModule::class,
        ViewModelModule::class,
        ViewModelModuleCardScreen::class,
    ]
)

interface AppComponent {
    fun inject(app: App)


    // Inject для проверки работы экранов и систем
    fun inject(hostActivity: HostActivity)


    fun getMainScreenViewModel(): com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance @BaseUrl baseUrl: String,
            @BindsInstance @PrefsKey prefsKey: String,
        ): AppComponent
    }
}




