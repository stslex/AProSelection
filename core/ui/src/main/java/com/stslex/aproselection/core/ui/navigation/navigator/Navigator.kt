package com.stslex.aproselection.core.ui.navigation.navigator

import com.stslex.aproselection.core.ui.navigation.destination.NavigationScreen

interface Navigator {

    fun navigate(screen: NavigationScreen)
}

