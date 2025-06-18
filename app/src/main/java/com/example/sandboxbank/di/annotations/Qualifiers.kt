package com.example.sandboxbank.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PrefsKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedPref

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PlainPref

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityContext