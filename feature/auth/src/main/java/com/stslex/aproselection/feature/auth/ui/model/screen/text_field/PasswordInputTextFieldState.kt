package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.platform.LocalHapticFeedback
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.base.PasswordTextFieldState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.hidden.HiddenState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action.InputAction

@Stable
data class PasswordInputTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val processAction: (InputAction.PasswordInput) -> Unit,
    private val hiddenState: HiddenState,
    override val text: String,
) : PasswordTextFieldState(
    hapticFeedback = hapticFeedback,
    hiddenState = hiddenState
) {

    override val hint: Int = R.string.auth_password_enter_hint_text

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(InputAction.PasswordInput(value))
        }
}

@Composable
fun rememberPasswordInputTextFieldState(
    processAction: (InputAction.PasswordInput) -> Unit,
    text: String,
): PasswordInputTextFieldState {
    val hapticFeedback = LocalHapticFeedback.current
    val hiddenState = remember { HiddenState() }
    return remember(text) {
        PasswordInputTextFieldState(
            hapticFeedback = hapticFeedback,
            processAction = processAction,
            hiddenState = hiddenState,
            text = text
        )
    }
}