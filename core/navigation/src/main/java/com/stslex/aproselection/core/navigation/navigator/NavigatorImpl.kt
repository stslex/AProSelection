package com.stslex.aproselection.core.navigation.navigator

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.destination.NavigationScreen

class NavigatorImpl(
    private val navController: NavHostController
) : Navigator {

    override fun navigate(screen: NavigationScreen) {
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navigateScreen(screen)
        }
    }

    private fun navigateScreen(screen: NavigationScreen) {
        val currentRoute = navController.currentDestination?.route ?: return
        if (currentRoute == screen.screenRoute) return

        navController.navigate(screen.screenRoute) {
            if (screen.isSingleTop.not()) return@navigate

            popUpTo(currentRoute) {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
        }
    }
}