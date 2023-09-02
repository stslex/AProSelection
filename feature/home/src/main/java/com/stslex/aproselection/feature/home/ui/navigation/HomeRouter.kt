package com.stslex.aproselection.feature.home.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.core.ui.base.daggerViewModel
import com.stslex.aproselection.feature.home.di.HomeComponentBuilder
import com.stslex.aproselection.feature.home.ui.HomeScreen
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Action

fun NavGraphBuilder.homeRouter(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        val component = HomeComponentBuilder.build(LocalContext.current.appApi)
        val viewModel = daggerViewModel<HomeViewModel> {
            component.getViewModelFactory()
        }

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