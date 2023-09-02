package com.stslex.aproselection.feature.auth.ui.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.core.ui.ext.CollectAsEvent
import com.stslex.aproselection.feature.auth.ui.AuthScreen
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import com.stslex.aproselection.feature.auth.ui.model.SnackbarActionType
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore

fun NavGraphBuilder.authRouter(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.AUTH.navigationRoute
    ) {
        val viewModel: AuthViewModel = viewModel()

        val snackbarHostState = remember { SnackbarHostState() }
        val state by remember { viewModel.state }.collectAsState()

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is AuthStore.Event.Navigation -> viewModel.processNavigation(event)
                is AuthStore.Event.ShowSnackbar -> {
                    val actionType = when (event) {
                        is AuthStore.Event.ShowSnackbar.SuccessRegister -> SnackbarActionType.SUCCESS
                        is AuthStore.Event.ShowSnackbar.ApiErrorSnackbar,
                        is AuthStore.Event.ShowSnackbar.SmthWentWrong -> SnackbarActionType.ERROR
                    }
                    val message = when (event) {
                        is AuthStore.Event.ShowSnackbar.ApiErrorSnackbar -> event.message
                        AuthStore.Event.ShowSnackbar.SmthWentWrong -> "smth when wrong"
                        AuthStore.Event.ShowSnackbar.SuccessRegister -> "success"
                    }
                    snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = actionType.action,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }

        val authScreenState = rememberAuthScreenState(
            snackbarHostState = snackbarHostState,
            screenState = state,
            processAction = {
                viewModel.sendAction(it)
            }
        )

        AuthScreen(
            state = authScreenState,
            modifier = modifier
        )
    }
}