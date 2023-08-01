package com.stslex.aproselection.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.navigation.NavigationHost

@Composable
fun InitialApp() {
    val navController = rememberNavController()

    Box {
        NavigationHost(
            navController = navController,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InitialAppPreview() {
    AppTheme {
        InitialApp()
    }
}