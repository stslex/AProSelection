package com.stslex.aproselection.feature.home.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.feature.home.ui.HomeScreen
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Action
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeRouter(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        val viewModel: HomeViewModel = koinViewModel()

        val state by remember {
            viewModel.state
        }.collectAsState()

        HomeScreen(
            modifier = modifier,
            logOut = remember {
                { viewModel.sendAction(Action.LogOut) }
            },
            users = remember { state.users }
        )
    }
}