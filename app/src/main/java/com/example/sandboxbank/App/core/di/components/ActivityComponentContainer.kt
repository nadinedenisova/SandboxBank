package com.example.sandboxbank.App.core.di.components

import android.content.Context

interface ActivityComponentContainer {

    val activityComponent: ActivityComponent

    fun createActivityComponent(context: Context)

    fun releaseActivityComponent()
}