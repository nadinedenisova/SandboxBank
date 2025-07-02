package com.example.sandboxbank.App.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.sandboxbank.App.core.di.annotations.ViewModelKey
import com.example.sandboxbank.App.ui.financialScreen.domain.FinancialScreenViewModel
import com.example.sandboxbank.App.ui.financialitem.FinancialItemDetailsViewModel
import com.example.sandboxbank.App.ui.mainscreen.domain.MainScreenViewModel
import com.example.sandboxbank.cards.ui.CardsScreenViewModel
import com.example.sandboxbank.auth.ui.viewmodel.AuthViewModel
import com.example.sandboxbank.cardmanager.cards.debit.ui.CardViewModel
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
    @ViewModelKey(CardsScreenViewModel::class)
    @Binds
    fun bindCardsScreenViewModel(cardsScreenViewModel: CardsScreenViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FinancialItemDetailsViewModel::class)
    @Binds
    fun bindFinancialItemDetailsViewModel(financialItemDetailsViewModel: FinancialItemDetailsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    @Binds
    fun bindTransactionViewModel(viewModel: TransactionViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

//      not yet implemented
//    @IntoMap
//    @ViewModelKey(CardViewModel::class)
//    @Binds
//    fun bindCardViewModel(viewModel: CardViewModel): ViewModel

}