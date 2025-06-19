package com.example.sandboxbank.App.core

import com.example.sandboxbank.Api
import com.example.sandboxbank.App.core.di.annotations.ApplicationScope
import com.example.sandboxbank.App.core.di.annotations.BaseUrl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit

@Module
object CoreAppModule {

//    @ApplicationScope
//    @Provides
//    @PlainPref
//    fun provideSharedPreferences(@PrefsKey prefsKey: String, context: Context): SharedPreferences {
//        return context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
//    }

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
}

