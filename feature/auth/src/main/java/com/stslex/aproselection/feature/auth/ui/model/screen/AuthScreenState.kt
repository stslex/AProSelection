package com.stslex.aproselection.feature.auth.ui.model.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.stslex.aproselection.core.ui.ext.CollectAsEvent
import com.stslex.aproselection.feature.auth.ui.model.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.model.ErrorType
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenEvent
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalComposeUiApi::class)
@Stable
data class AuthScreenState(
    val screenLoadingState: ScreenLoadingState = ScreenLoadingState.Content,
    val username: String = "",
    val password: String = "",
    val passwordSubmit: String = "",
    val authFieldsState: AuthFieldsState = AuthFieldsState.AUTH,
    val snackbarHostState: SnackbarHostState,
    private val processAction: (ScreenAction) -> Unit,
    private val keyboardController: SoftwareKeyboardController? = null
) {
    val isFieldsValid: Boolean
        get() {
            val isCorrectLength = username.length >= 4 && password.length >= 4
            val isEqualsPasswords = password == passwordSubmit
            val isRegisterPassword = authFieldsState == AuthFieldsState.AUTH || isEqualsPasswords
            return isCorrectLength && isRegisterPassword
        }

    val isRegisterState = authFieldsState == AuthFieldsState.REGISTER

    fun onUsernameChange(username: String) {
        if (this.username == username) return
        processAction(ScreenAction.UsernameInput(username))
    }

    fun onPasswordChange(password: String) {
        if (this.password == password) return
        processAction(ScreenAction.PasswordInput(password))
    }

    fun onPasswordSubmitChange(passwordSubmit: String) {
        if (this.passwordSubmit == passwordSubmit) return
        processAction(ScreenAction.PasswordSubmitInput(passwordSubmit))
    }

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

    val keyboardController = LocalSoftwareKeyboardController.current

    return remember(screenState) {
        AuthScreenState(
            screenLoadingState = screenState.screenLoadingState,
            username = screenState.username,
            password = screenState.password,
            passwordSubmit = screenState.passwordSubmit,
            authFieldsState = screenState.authFieldsState,
            snackbarHostState = snackbarHostState,
            processAction = processAction,
            keyboardController = keyboardController
        )
    }
}
