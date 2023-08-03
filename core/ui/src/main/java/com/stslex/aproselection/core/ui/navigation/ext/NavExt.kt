package com.stslex.aproselection.core.ui.navigation.ext

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.stslex.aproselection.core.ui.navigation.destination.AppDestination

object NavExt {

    val AppDestination.composableArguments: List<NamedNavArgument>
        get() = argsNames.map { name ->
            navArgument(name) { NavType.StringType }
        }

    val AppDestination.parseArguments: NavBackStackEntry.() -> List<String>
        get() = {
            argsNames.map { name ->
                arguments?.getString(name).orEmpty()
            }
        }
}