package com.example.sandboxbank.di.components

import android.content.Context
import com.example.sandboxbank.MainActivity
import com.example.sandboxbank.di.annotations.ActivityContext
import com.example.sandboxbank.di.annotations.ActivityScope
import com.example.sandboxbank.di.modules.PinCodeModule
import com.example.sandboxbank.di.modules.ViewModelFactoryModule
import com.example.sandboxbank.di.modules.ViewModelModule
import com.example.sandboxbank.pinCode.PinCodeViewModel
import com.example.sandboxbank.profile.di.ProfileModule
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        PinCodeModule::class,
        ViewModelModule::class,
        ProfileModule::class,
    ]
)

interface ActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun context(@ActivityContext context: Context): Builder

        fun build(): ActivityComponent
    }
}

