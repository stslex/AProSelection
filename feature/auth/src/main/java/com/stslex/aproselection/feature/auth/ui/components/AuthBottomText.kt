package com.stslex.aproselection.feature.auth.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import com.stslex.aproselection.feature.auth.ui.model.AuthFieldsState

@Composable
fun AuthBottomText(
    authFieldsState: AuthFieldsState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    TextButton(
        modifier = modifier,
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        }
    ) {
        Text(
            text = stringResource(
                id = authFieldsState.inverse.buttonResId
            ),
            style = MaterialTheme.typography.titleMedium
        )
    }
}