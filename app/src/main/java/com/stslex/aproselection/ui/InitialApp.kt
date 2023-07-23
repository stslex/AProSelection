package com.stslex.aproselection.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.navigation.NavigationHost

@Composable
fun InitialApp() {
    val navController = rememberNavController()

    NavigationHost(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun InitialAppPreview() {
    AppTheme {
        InitialApp()
    }
}