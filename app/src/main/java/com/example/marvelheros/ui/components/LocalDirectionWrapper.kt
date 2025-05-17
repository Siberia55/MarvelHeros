package com.example.marvelheros.ui.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection


@Composable
fun LocalDirectionWrapper(content: @Composable () -> Unit) {
    val config = LocalConfiguration.current
    val layoutDirection = if (config.locales.get(0).language in listOf("ar", "iw", "fa")) {
        LayoutDirection.Rtl
    } else {
        LayoutDirection.Ltr
    }
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        content()
    }
}