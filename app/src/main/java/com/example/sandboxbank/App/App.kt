package com.example.sandboxbank.App

import android.app.Application
import android.content.Context
import com.example.sandboxbank.App.core.di.components.ActivityComponent
import com.example.sandboxbank.App.core.di.components.AppComponent
import com.example.sandboxbank.App.core.di.components.DaggerAppComponent
import com.example.sandboxbank.App.core.di.components.ComponentContainer
import com.example.sandboxbank.App.core.di.modules.ProfileModule.SETTING_FILE_NAME
import com.example.sandboxbank.App.ui.designkit.mode.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.baseDarkPalette
import com.example.sandboxbank.App.ui.designkit.mode.baseLightPalette
import com.example.sandboxbank.profile.domain.SettingStoreManager
import javax.inject.Inject

private const val APP_PREFERENCES = "app_preferences"

class App : Application(), ComponentContainer {

    override val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.factory()
            .create(
                context = this,
                baseUrl = "https://c210-87-253-62-46.ngrok-free.app",
                prefsKey = APP_PREFERENCES,
            )
    }

    private var _activityComponent: ActivityComponent? = null

    override fun createActivityComponent(context: Context) {
        _activityComponent = appComponent
            .provideActivityComponent()
            .context(context)
            .build()
    }

    override fun releaseActivityComponent() {
        _activityComponent = null
    }

    override val activityComponent: ActivityComponent
        get() = _activityComponent
            ?: error("ActivityComponent не создан. Сначала вызови createActivityComponent(context)")


    override fun onCreate() {
        applicationInstance = this
        appComponent.inject(this)

        val shared = applicationContext.getSharedPreferences(SETTING_FILE_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = shared.getBoolean("darkTheme", false)
        if(!isDarkTheme){
            ColorSingleton.appPalette = baseLightPalette
        }
        else{
            ColorSingleton.appPalette = baseDarkPalette
        }

        super.onCreate()
    }



    companion object {

        lateinit var applicationInstance: App
            private set

        @JvmStatic
        val componentsContainer: ComponentContainer
            get() = applicationInstance
    }
}

val Context.appContext: AppComponent
    get() = when(this){
        is App -> appComponent
        else -> (this.applicationContext as App).appComponent
    }