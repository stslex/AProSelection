package com.stslex.aproselection.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.aproselection.core.network.AppDestination
import com.stslex.aproselection.core.network.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.navigation.authRouter

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.AUTH
) {
    val navigator: (NavigationScreen) -> Unit = { screen ->
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navController.navigateScreen(screen)
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        authRouter(modifier, navigator)
    }
}

fun NavHostController.navigateScreen(screen: NavigationScreen) {
    navigate(screen.screenRoute) {
        if (screen.isSingleTop.not()) return@navigate
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = false
    }
}