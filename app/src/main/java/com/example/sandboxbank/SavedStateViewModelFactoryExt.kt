@file:Suppress("ktlint:standard:filename", "Filename", "MatchingDeclarationName")

package com.example.sandboxbank

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.sandboxbank.di.ViewModelFactory

@Composable
fun AppCompatActivity.LocalSavedStateViewModelFactoryProvider(
    assistedFactory: ViewModelFactory,
    content: @Composable () -> Unit,
) {
    val bundle = intent.extras ?: throw IllegalStateException("Activity $this does not have any extras")

    CompositionLocalProvider(
        LocalSavedStateViewModelFactory provides assistedFactory,
        LocalSavedStateBundle provides bundle,
        content = content,
    )
}


val LocalSavedStateViewModelFactory: ProvidableCompositionLocal<ViewModelFactory> = staticCompositionLocalOf {
    error("SavedStateViewModelFactory not provided to CompositionLocal")
}

val LocalSavedStateBundle: ProvidableCompositionLocal<Bundle> = staticCompositionLocalOf {
    error("SavedStateBundle not provided to CompositionLocal")
}

