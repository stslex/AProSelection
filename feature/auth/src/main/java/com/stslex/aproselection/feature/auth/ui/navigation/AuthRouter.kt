package com.stslex.aproselection.feature.auth.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.ui.navigation.AppDestination
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.AuthScreen
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authRouter(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.AUTH.navigationRoute
    ) {
        val viewModel: AuthViewModel = koinViewModel(
            parameters = { parametersOf(navigate) }
        )

        val authScreenState = rememberAuthScreenState(
            screenStateFlow = viewModel::screenState,
            screenEventFlow = viewModel::screenEvent,
            processAction = viewModel::process
        )

        AuthScreen(
            state = authScreenState,
            modifier = modifier
        )
    }
}