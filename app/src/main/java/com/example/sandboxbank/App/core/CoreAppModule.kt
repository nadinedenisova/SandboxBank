package com.example.sandboxbank.App.core

import android.content.Context
import android.content.SharedPreferences
import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.BaseUrl
import com.example.sandboxbank.App.core.di.annotations.PrefsKey
import com.example.sandboxbank.App.ui.debitcards.debit.model.data.RemoteCardRepository
import com.example.sandboxbank.App.ui.debitcards.utils.InternetUtil
import com.example.sandboxbank.cardmanager.cards.debit.model.data.CardRepository
import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
object CoreAppModule {

    @ApplicationScope
    @Provides
    fun provideSharedPreferences(@PrefsKey prefsKey: String, context: Context): SharedPreferences {
        return context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
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
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        @BaseUrl baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
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
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): CardRepository {
        return CardRepository(sharedPreferences, gson)
    }

    @Provides
    @ApplicationScope
    fun provideRemoteCardRepository(
        api: Api,
        sharedPrefs: SharedPreferences
    ): RemoteCardRepository {
        val tokenProvider: suspend () -> String = {
            sharedPrefs.getString("access_token", "") ?: ""
        }
        return RemoteCardRepository(api, tokenProvider)
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @ApplicationScope
    @Provides
    fun provideInternetUtil(context: Context): InternetUtil {
        return InternetUtil(context)
    }


}


