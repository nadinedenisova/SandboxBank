package com.example.sandboxbank.profile.di

import com.example.sandboxbank.profile.domain.ProfileScreenViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ProfileModule {

    @Singleton
    @Provides
    fun provideSettingScreenViewModel(): ProfileScreenViewModel {
        return ProfileScreenViewModel()
    }


}