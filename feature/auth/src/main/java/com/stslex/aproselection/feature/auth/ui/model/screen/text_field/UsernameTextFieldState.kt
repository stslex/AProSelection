package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.base.AuthTextField
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action.InputAction

@Stable
data class UsernameTextFieldState(
    private val processAction: (InputAction.UsernameInput) -> Unit,
    override val text: String,
) : AuthTextField() {

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(InputAction.UsernameInput(value))
        }

    override val label: Int = R.string.auth_username_text
}

@Composable
fun rememberUsernameTextFieldState(
    text: String,
    processAction: (InputAction.UsernameInput) -> Unit
): UsernameTextFieldState = remember(text) {
    UsernameTextFieldState(
        text = text,
        processAction = processAction
    )
}
