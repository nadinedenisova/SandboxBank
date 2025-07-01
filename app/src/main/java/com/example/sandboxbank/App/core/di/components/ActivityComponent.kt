package com.example.sandboxbank.App.core.di.components

import android.content.Context
import com.example.sandboxbank.App.core.di.annotations.ActivityContext
import com.example.sandboxbank.App.core.di.annotations.ActivityScope
import com.example.sandboxbank.App.core.di.modules.AuthModule
import com.example.sandboxbank.App.core.di.modules.NetworkModule
import com.example.sandboxbank.App.core.di.modules.PinCodeModule
import com.example.sandboxbank.App.core.di.modules.ProfileModule
import com.example.sandboxbank.App.core.di.modules.StorageModule
import com.example.sandboxbank.App.core.di.modules.ViewModelFactoryModule
import com.example.sandboxbank.App.core.di.modules.ViewModelModule
import com.example.sandboxbank.main.ui.HostActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        PinCodeModule::class,
        ViewModelModule::class,
        ProfileModule::class,
        AuthModule::class,
        NetworkModule::class,
        StorageModule::class
    ]
)

interface ActivityComponent {
    fun inject(activity: HostActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun context(@ActivityContext context: Context): Builder

        fun build(): ActivityComponent
    }
}

