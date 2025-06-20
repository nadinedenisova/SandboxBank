package com.example.sandboxbank.App.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.App.core.di.annotations.ViewModelKey
import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel
import com.example.sandboxbank.pinCode.PinCodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PinCodeViewModel::class)
    fun bindPinCodeViewModel(pinCodeViewModel: PinCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CardViewModel::class)
    fun bindCardViewModel(cardViewModel: CardViewModel): ViewModel
}
