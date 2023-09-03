package com.stslex.aproselection.core.navigation.navigator

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.core.Logger
import com.stslex.aproselection.core.navigation.destination.NavigationScreen
import com.stslex.aproselection.core.ui.di.Screen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor(
    private val navController: NavHostController
) : com.stslex.aproselection.core.ui.di.Navigator {

    override fun navigate(screen: Screen) {
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            is NavigationScreen -> navigateScreen(screen)
            else -> {
                Logger.debug("unresolve navigation route", this::class.simpleName)
            }
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