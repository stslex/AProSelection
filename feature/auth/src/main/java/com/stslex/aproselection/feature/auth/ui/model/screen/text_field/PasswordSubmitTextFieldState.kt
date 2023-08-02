package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.hapticfeedback.HapticFeedback
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.PasswordSubmitInput

data class PasswordSubmitTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val processAction: (PasswordSubmitInput) -> Unit,
    override val text: String,
) : PasswordTextFieldState(hapticFeedback) {

    override val hint: Int = R.string.auth_password_submit_hint_text

    override val sendAction: (text: String) -> Unit
        get() = { value ->
            processAction(PasswordSubmitInput(value))
        }
}

@Composable
fun rememberPasswordSubmitTextFieldState(
    hapticFeedback: HapticFeedback,
    processAction: (PasswordSubmitInput) -> Unit,
    text: String,
) = remember(text) {
    PasswordSubmitTextFieldState(
        hapticFeedback = hapticFeedback,
        processAction = processAction,
        text = text
    )
}