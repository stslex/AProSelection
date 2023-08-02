package com.stslex.aproselection.feature.auth.ui.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.AuthPasswordTextField
import com.stslex.aproselection.feature.auth.ui.AuthUsernameTextField
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun AuthFieldsColumn(
    state: AuthScreenState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthUsernameTextField(
            inputUsername = state.username,
            onTextChange = state::onUsernameChange
        )
        Spacer(Modifier.height(AppDimens.Padding.medium))
        AuthPasswordTextField(
            inputPassword = state.password,
            onTextChange = state::onPasswordChange
        )
        AnimatedVisibility(
            visible = state.isRegisterState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(AppDimens.Padding.small))
                AuthPasswordTextField(
                    inputPassword = state.passwordSubmit,
                    onTextChange = state::onPasswordSubmitChange
                )
            }
        }
        Spacer(Modifier.height(AppDimens.Padding.big))
        AuthSubmitButton(
            isValid = state.isFieldsValid,
            onClick = state::onSubmitClicked,
        )
    }
}

@Preview(
    device = "id:pixel_6a", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AuthFieldsColumnPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            AuthFieldsColumn(
                modifier = Modifier.align(Alignment.Center),
                state = rememberAuthScreenState(
                    screenStateFlow = {
                        MutableStateFlow(ScreenState())
                    },
                    screenEventFlow = {
                        MutableSharedFlow()
                    },
                    processAction = {}
                )
            )
        }
    }
}