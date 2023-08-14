package com.stslex.aproselection.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.core.navigation.ext.NavExt.isAuth
import com.stslex.aproselection.navigation.NavigationHost
import com.stslex.aproselection.ui.components.AppToolbar
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (navController.isAuth.not()) {
                    AppToolbar()
                }
                NavigationHost(
                    modifier = Modifier.weight(1f),
                    navController = navController,
                    startDestination = destination
                )
            }
        }
}
