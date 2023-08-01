package com.stslex.aproselection.core.ui.navigation

sealed interface NavigationScreen {

    val screen: AppDestination

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    val isSingleTop: Boolean
        get() = false

    val appArgs: AppArguments
        get() = AppArguments.Empty

    data object Auth : NavigationScreen {
        override val screen: AppDestination = AppDestination.AUTH
        override val isSingleTop: Boolean = true
    }

    data object Home : NavigationScreen {
        override val screen: AppDestination = AppDestination.HOME
        override val isSingleTop: Boolean = true
    }

    data object Splash: NavigationScreen{
        override val screen: AppDestination = AppDestination.SPLASH
        override val isSingleTop: Boolean = true
    }

    data object PopBackStack : NavigationScreen {
        override val screen: AppDestination = throw Exception("PopBackStack")
        override val appArgs: AppArguments = throw Exception("PopBackStack")
    }
}