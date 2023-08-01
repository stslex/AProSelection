package com.stslex.aproselection.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.ui.navigation.AppDestination
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.navigation.authRouter
import com.stslex.aproselection.feature.home.ui.navigation.homeRouter
import com.stslex.aproselection.ui.InitialAppViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.SPLASH
) {
    val navigator: (NavigationScreen) -> Unit = { screen ->
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navController.navigateScreen(screen)
        }
    }

    val viewModel = koinViewModel<InitialAppViewModel>(
        parameters = { parametersOf(navigator) }
    )
    val isAuth by remember {
        viewModel.isAuth
    }.collectAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(NavigationScreen.Splash.screenRoute) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(text = "splash") // TODO replace with norm navigator
            }
            val screen = when (isAuth) {
                true -> NavigationScreen.Home
                false -> NavigationScreen.Auth
                null -> null
            }
            screen?.let(navigator)
        }
        authRouter(modifier, navigator)
        homeRouter(modifier, navigator)
    }
}

fun NavHostController.navigateScreen(screen: NavigationScreen) {
    val currentRoute = this.currentDestination?.route
    if (currentRoute == screen.screenRoute) return
    navigate(screen.screenRoute) {
        if (screen.isSingleTop.not()) return@navigate
        currentRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
                saveState = true
            }
        }

        launchSingleTop = true
    }
}