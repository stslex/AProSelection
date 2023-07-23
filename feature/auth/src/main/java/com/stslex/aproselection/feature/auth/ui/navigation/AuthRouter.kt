package com.stslex.aproselection.feature.auth.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.ui.navigation.AppDestination
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.AuthScreen
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authRouter(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.AUTH.navigationRoute
    ) {
        val viewModel: AuthViewModel = koinViewModel()

        val text by remember {
            viewModel.text
        }.collectAsState()

        AuthScreen(
            text = text,
            navigate = navigate,
            setUsername = viewModel::setUsername,
            modifier = modifier
        )
    }
}