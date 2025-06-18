package com.example.sandboxbank.App.core.di.components

import android.content.Context
import com.example.sandboxbank.App.core.di.annotations.ActivityContext
import com.example.sandboxbank.HostActivity
import com.example.sandboxbank.App.core.di.annotations.ActivityScope
import com.example.sandboxbank.App.core.di.modules.PinCodeModule
import com.example.sandboxbank.App.core.di.modules.ViewModelFactoryModule
import com.example.sandboxbank.App.core.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        PinCodeModule::class,
        ViewModelModule::class,
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

