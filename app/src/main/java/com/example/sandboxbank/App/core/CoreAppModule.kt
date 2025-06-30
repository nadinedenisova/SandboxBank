package com.example.sandboxbank.App.core

import android.content.Context
import android.content.SharedPreferences
import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.deposit.data.network.FinancialItemNetworkRepositoryImpl
import com.example.sandboxbank.App.core.deposit.domain.db.FinancialItemRepository
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.AppContext
import com.example.sandboxbank.App.core.di.annotations.BaseUrl
import com.example.sandboxbank.App.core.di.annotations.PlainPref
import com.example.sandboxbank.App.core.di.annotations.PrefsKey
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.example.sandboxbank.cardmanager.cards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.profile.domain.GetStoreManager
import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit

@Module
object CoreAppModule {

    @ApplicationScope
    @Provides
    @PlainPref
    fun provideSharedPreferences(
        @PrefsKey prefsKey: String,
        @AppContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
    }

    @ApplicationScope
    @Provides
    @PlainPref
    fun provideGetStoreManager(@PlainPref sharedPreferences: SharedPreferences): GetStoreManager {
        return GetStoreManager(sharedPreferences)
    }

    @ApplicationScope
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        @BaseUrl baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()

    @ApplicationScope
    @Provides
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    @ApplicationScope
    @Provides
    fun provideGson(): Gson = Gson()

    @ApplicationScope
    @Provides
    fun provideCardRepository(
        @PlainPref sharedPreferences: SharedPreferences,
        gson: Gson
    ): CardRepository {
        return CardRepository(sharedPreferences, gson)
    }

    @Provides
    @ApplicationScope
    fun provideRemoteCardRepository(
        api: Api,
        @PlainPref sharedPrefs: SharedPreferences,
        gson: Gson
    ): RemoteCardRepository {
        return RemoteCardRepository(api, sharedPrefs, gson)
    }


    @Provides
    @ApplicationScope
    fun provideFinancialItemRepository(
        api: Api,
    ): FinancialItemRepository {
        return FinancialItemNetworkRepositoryImpl(api)
    }



    @ApplicationScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @ApplicationScope
    @Provides
    fun provideInternetUtil(@AppContext context: Context): InternetUtil {
        return InternetUtil(context)
    }
}
