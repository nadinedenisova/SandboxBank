package com.example.sandboxbank.App.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.App.core.di.annotations.ViewModelKey
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.pinCode.PinCodeViewModel
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
import com.example.sandboxbank.transaction.domain.TransactionViewModel
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

    @IntoMap
    @ViewModelKey(FinancialScreenViewModel::class)
    @Binds
    fun bindFinancialScreenViewModel(financialScreenViewModel: FinancialScreenViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FinancialItemDetailsViewModel::class)
    @Binds
    fun bindFinancialItemDetailsViewModel(financialItemDetailsViewModel: FinancialItemDetailsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    @Binds
    fun bindTransactionViewModel(viewModel: TransactionViewModel): ViewModel
}