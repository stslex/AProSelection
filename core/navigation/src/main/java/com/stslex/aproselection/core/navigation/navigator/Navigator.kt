package com.stslex.aproselection.core.navigation.navigator

import com.stslex.aproselection.core.navigation.destination.NavigationScreen

interface Navigator {

    fun navigate(screen: NavigationScreen)
}

