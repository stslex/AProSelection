package com.stslex.aproselection.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stslex.aproselection.navigation.NavigationHost
import com.stslex.aproselection.core.ui.theme.AppTheme

@Composable
fun InitialApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

    DisposableEffect(systemUiController, isDarkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = isDarkTheme.not(),
        )
        onDispose {}
    }

    NavigationHost(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun InitialAppPreview() {
    AppTheme {
        InitialApp()
    }
}