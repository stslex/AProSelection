package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.UsernameInput
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.base.AuthTextField

@Stable
data class UsernameTextFieldState(
    private val processAction: (UsernameInput) -> Unit,
    override val text: String,
) : AuthTextField() {

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(UsernameInput(value))
        }

    override val label: Int = R.string.auth_username_text
}

@Composable
fun rememberUsernameTextFieldState(
    text: String,
    processAction: (UsernameInput) -> Unit
): UsernameTextFieldState = remember(text) {
    UsernameTextFieldState(
        text = text,
        processAction = processAction
    )
}
