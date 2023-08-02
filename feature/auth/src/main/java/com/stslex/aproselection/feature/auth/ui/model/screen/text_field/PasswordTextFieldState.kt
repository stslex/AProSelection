package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.stslex.aproselection.feature.auth.R

abstract class PasswordTextFieldState(
    private val hapticFeedback: HapticFeedback
) : AuthTextField() {

    private val isPasswordHidden = mutableStateOf(true)

    override val label: Int = R.string.auth_password_text
    abstract val hint: Int

    val visualTransformation = derivedStateOf {
        if (isPasswordHidden.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }

    val trailingIconRes = derivedStateOf {
        if (isPasswordHidden.value) {
            R.drawable.baseline_visibility_off_24
        } else {
            R.drawable.baseline_visibility_24
        }
    }

    fun onPasswordHideClicked() {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        isPasswordHidden.value = isPasswordHidden.value.not()
    }
}

