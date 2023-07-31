package com.stslex.aproselection.feature.auth.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.ui.ext.CollectAsEvent
import com.stslex.aproselection.core.ui.navigation.AppDestination
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.AuthScreen
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import com.stslex.aproselection.feature.auth.ui.model.ScreenEvent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
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

        val snackbarHostState = remember {
            SnackbarHostState()
        }

        val screenState by remember {
            viewModel.screenState
        }.collectAsState()

        viewModel.screenEvent.CollectAsEvent { event ->
            when (event) {
                is ScreenEvent.Error -> snackbarHostState.showSnackbar(event.throwable.message.orEmpty())
            }
        }

        AuthScreen(
            screenState = screenState,
            snackbarHostState = snackbarHostState,
            register = viewModel::register,
            auth = viewModel::auth,
            modifier = modifier
        )
    }
}