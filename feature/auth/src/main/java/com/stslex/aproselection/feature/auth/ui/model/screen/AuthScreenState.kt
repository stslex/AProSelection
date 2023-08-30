package com.stslex.aproselection.feature.auth.ui.model.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.PasswordInputTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.PasswordSubmitTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.UsernameTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberPasswordInputTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberPasswordSubmitTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.rememberUsernameTextFieldState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.ScreenLoadingState

@OptIn(ExperimentalComposeUiApi::class)
@Stable
data class AuthScreenState(
    val screenLoadingState: ScreenLoadingState = ScreenLoadingState.Content,
    val usernameState: UsernameTextFieldState,
    val passwordEnterState: PasswordInputTextFieldState,
    val passwordSubmitState: PasswordSubmitTextFieldState,
    val authFieldsState: AuthFieldsState = AuthFieldsState.AUTH,
    val snackbarHostState: SnackbarHostState,
    private val processAction: (Action) -> Unit,
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
        processAction(Action.OnSubmitClicked)
    }

    fun onAuthFieldChange() {
        processAction(Action.OnAuthFieldChange)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberAuthScreenState(
    screenState: AuthStore.State,
    snackbarHostState: SnackbarHostState,
    processAction: (Action) -> Unit,
): AuthScreenState {
    val keyboardController = LocalSoftwareKeyboardController.current

    val usernameTextFieldState = rememberUsernameTextFieldState(
        text = screenState.username,
        processAction = processAction
    )

    val passwordEnterState = rememberPasswordInputTextFieldState(
        processAction = processAction,
        text = screenState.password
    )

    val passwordSubmitState = rememberPasswordSubmitTextFieldState(
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
