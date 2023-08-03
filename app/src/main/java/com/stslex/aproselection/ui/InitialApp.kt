package com.stslex.aproselection.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.ui.navigation.destination.AppDestination
import com.stslex.aproselection.navigation.NavigationHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitialApp(
    navController: NavHostController
) {
    val viewModel: InitialAppViewModel = koinViewModel()
    val isInitialAuth by remember {
        viewModel.isInitialAuth
    }.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    AppDestination
        .getStartDestination(isInitialAuth)
        ?.let { destination ->
            NavigationHost(
                navController = navController,
                startDestination = destination
            )
        }
}
