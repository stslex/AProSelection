package com.stslex.aproselection.feature.auth.ui.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.AuthUsernameTextField
import com.stslex.aproselection.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore

@Composable
fun AuthFieldsColumn(
    state: AuthScreenState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            horizontal = AppDimens.Padding.big
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthUsernameTextField(state.usernameState)
        Spacer(Modifier.height(AppDimens.Padding.medium))
        AuthPasswordTextField(state.passwordEnterState)
        AnimatedVisibility(
            visible = state.isRegisterState,
            enter = fadeIn(tween(300)) + expandVertically(tween(600)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(600))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(AppDimens.Padding.small))
                AuthPasswordTextField(state.passwordSubmitState)
            }
        }
        Spacer(Modifier.height(AppDimens.Padding.big))
        AuthSubmitButton(
            isValid = state.isFieldsValid,
            authFieldsState = state.authFieldsState,
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
                    screenState = AuthStore.State(
                        screenLoadingState = AuthStore.ScreenLoadingState.Content,
                        username = "",
                        password = "",
                        passwordSubmit = "",
                        authFieldsState = AuthStore.AuthFieldsState.AUTH
                    ),
                    snackbarHostState = SnackbarHostState(),
                    processAction = {}
                )
            )
        }
    }
}