package com.stslex.aproselection.feature.auth.ui.model.screen.text_field.base

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.hidden.HiddenState

@Stable
abstract class PasswordTextFieldState(
    private val hapticFeedback: HapticFeedback,
    private val hiddenState: HiddenState
) : AuthTextField() {

    override val label: Int = R.string.auth_password_text
    abstract val hint: Int

    val visualTransformation = derivedStateOf {
        if (hiddenState.isHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }

    val trailingIconRes = derivedStateOf {
        if (hiddenState.isHidden) {
            R.drawable.baseline_visibility_off_24
        } else {
            R.drawable.baseline_visibility_24
        }
    }

    fun onPasswordHideClicked() {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        hiddenState.isHidden = hiddenState.isHidden.not()
    }
}

