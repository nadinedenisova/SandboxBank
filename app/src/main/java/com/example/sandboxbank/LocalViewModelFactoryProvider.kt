package com.example.sandboxbank

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun LocalViewModelFactoryProvider(
    viewModelFactory: ViewModelProvider.Factory,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalViewModelFactory provides viewModelFactory, content)
}

val LocalViewModelFactory: ProvidableCompositionLocal<ViewModelProvider.Factory> = staticCompositionLocalOf {
    error("ViewModelProvider.Factory not provided to CompositionLocal")
}

@Composable
inline fun <reified VM : ViewModel> viewModel(): VM {
    return androidx.lifecycle.viewmodel.compose.viewModel<VM>(factory = LocalViewModelFactory.current)
}