package com.stslex.aproselection.feature.auth.ui.model.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.stslex.aproselection.core.ui.ext.CollectAsEvent
import com.stslex.aproselection.feature.auth.ui.model.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.model.ErrorType
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenEvent
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.PasswordInputTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.PasswordSubmitTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.UsernameTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberPasswordInputTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberPasswordSubmitTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberUsernameTextFieldState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Stable
data class AuthScreenState(
    val screenLoadingState: ScreenLoadingState = ScreenLoadingState.Content,
    val usernameState: UsernameTextFieldState,
    val passwordEnterState: PasswordInputTextFieldState,
    val passwordSubmitState: PasswordSubmitTextFieldState,
    val authFieldsState: AuthFieldsState = AuthFieldsState.AUTH,
    val snackbarHostState: SnackbarHostState,
    private val processAction: (ScreenAction) -> Unit,
    private val keyboardController: SoftwareKeyboardController? = null
) {

    val isFieldsValid: Boolean
        get() {
            val isCorrectLength = usernameState.text.length >= 4 &&
                    passwordEnterState.text.length >= 4
            val isEqualsPasswords = passwordEnterState.text == passwordSubmitState.text
            val isRegisterPassword = authFieldsState == AuthFieldsState.AUTH || isEqualsPasswords
            return isCorrectLength && isRegisterPassword
        }

    val isRegisterState = authFieldsState == AuthFieldsState.REGISTER

    fun onSubmitClicked() {
        keyboardController?.hide()
        processAction(ScreenAction.OnSubmitClicked)
    }

    fun onAuthFieldChange() {
        processAction(ScreenAction.OnAuthFieldChange)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberAuthScreenState(
    screenStateFlow: () -> StateFlow<ScreenState>,
    screenEventFlow: () -> SharedFlow<ScreenEvent>,
    processAction: (ScreenAction) -> Unit,
): AuthScreenState {
    val keyboardController = LocalSoftwareKeyboardController.current
    val hapticFeedback = LocalHapticFeedback.current

    val screenState by remember {
        screenStateFlow()
    }.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    screenEventFlow().CollectAsEvent { event ->
        when (event) {
            is ScreenEvent.Error -> when (val error = event.errorType) {
                is ErrorType.UnResolve -> {
                    snackbarHostState.showSnackbar(error.throwable.message.orEmpty())
                }

                is ErrorType.Api -> Unit // TODO add api parse error logic
            }
        }
    }

    val usernameTextFieldState = rememberUsernameTextFieldState(
        text = screenState.username,
        processAction = processAction
    )

    val passwordEnterState = rememberPasswordInputTextFieldState(
        hapticFeedback = hapticFeedback,
        processAction = processAction,
        text = screenState.password
    )

    val passwordSubmitState = rememberPasswordSubmitTextFieldState(
        hapticFeedback = hapticFeedback,
        processAction = processAction,
        text = screenState.passwordSubmit
    )

    return remember(screenState) {
        AuthScreenState(
            screenLoadingState = screenState.screenLoadingState,
            passwordEnterState = passwordEnterState,
            passwordSubmitState = passwordSubmitState,
            authFieldsState = screenState.authFieldsState,
            snackbarHostState = snackbarHostState,
            processAction = processAction,
            keyboardController = keyboardController,
            usernameState = usernameTextFieldState
        )
    }
}
