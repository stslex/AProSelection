package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.PasswordInput

data class PasswordInputTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val processAction: (PasswordInput) -> Unit,
    override val text: String,
) : PasswordTextFieldState(hapticFeedback) {

    override val hint: Int = R.string.auth_password_enter_hint_text

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(PasswordInput(value))
        }
}

@Composable
fun rememberPasswordInputTextFieldState(
    hapticFeedback: HapticFeedback,
    processAction: (PasswordInput) -> Unit,
    text: String,
) = remember(text) {
    PasswordInputTextFieldState(
        hapticFeedback = hapticFeedback,
        processAction = processAction,
        text = text
    )
}