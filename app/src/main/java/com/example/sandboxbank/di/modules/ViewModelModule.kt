package com.example.sandboxbank.di.modules

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.di.annotations.ViewModelKey
import com.example.sandboxbank.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.pinCode.PinCodeViewModel
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
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

    @IntoMap
    @ViewModelKey(ProfileScreenViewModel::class)
    @Binds
    fun bindProfileScreenViewModel(profileScreenViewModel: ProfileScreenViewModel): ViewModel
}