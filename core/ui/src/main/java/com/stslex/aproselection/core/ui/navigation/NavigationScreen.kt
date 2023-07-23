package com.stslex.aproselection.core.ui.navigation

sealed class NavigationScreen {

    abstract val screen: AppDestination

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    open val isSingleTop: Boolean
        get() = false

    open val appArgs: AppArguments
        get() = AppArguments.Empty

    object Auth : NavigationScreen() {

        override val screen: AppDestination = AppDestination.AUTH
        override val isSingleTop: Boolean = true
    }

    object PopBackStack : NavigationScreen() {
        override val screen: AppDestination = throw Exception("PopBackStack")
        override val appArgs: AppArguments = throw Exception("PopBackStack")
    }
}