package com.stslex.aproselection.feature.home.ui.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.feature.home.ui.HomeScreen
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeRouter(
    modifier: Modifier = Modifier,
) {
    composable(
        route = com.stslex.aproselection.core.navigation.destination.AppDestination.HOME.navigationRoute
    ) {
        val viewModel: HomeViewModel = koinViewModel()

        HomeScreen(
            modifier = modifier,
            logOut = viewModel::logOut,
            users = remember { viewModel::users }
        )
    }
}