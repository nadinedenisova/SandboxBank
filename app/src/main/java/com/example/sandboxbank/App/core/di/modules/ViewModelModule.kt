package com.example.sandboxbank.App.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.App.core.di.annotations.ViewModelKey
import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.pinCode.PinCodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(PinCodeViewModel::class)
    @Binds
    fun bindPinCodeViewModel(pinCodeViewModel: PinCodeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    @Binds
    fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel
}