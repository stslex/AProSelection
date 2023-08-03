package com.stslex.aproselection.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.aproselection.core.ui.navigation.destination.AppDestination
import com.stslex.aproselection.feature.auth.ui.navigation.authRouter
import com.stslex.aproselection.feature.home.ui.navigation.homeRouter

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: AppDestination,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        authRouter(modifier)
        homeRouter(modifier)
    }
}
