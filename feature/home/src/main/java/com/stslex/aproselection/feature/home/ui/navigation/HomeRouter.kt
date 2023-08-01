package com.stslex.aproselection.feature.home.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.ui.navigation.AppDestination
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.home.ui.HomeScreen
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.homeRouter(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        val viewModel: HomeViewModel = koinViewModel(
            parameters = { parametersOf(navigate) }
        )

        HomeScreen(
            modifier = modifier,
            logOut = viewModel::logOut
        )
    }
}