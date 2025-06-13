package com.example.sandboxbank.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PrefsKey