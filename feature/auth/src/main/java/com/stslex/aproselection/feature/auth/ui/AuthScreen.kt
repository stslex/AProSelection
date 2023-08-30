package com.stslex.aproselection.feature.auth.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.swipeable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.components.ErrorSnackbar
import com.stslex.aproselection.core.ui.components.SuccessSnackbar
import com.stslex.aproselection.core.ui.ext.toPx
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.components.AuthFieldsColumn
import com.stslex.aproselection.feature.auth.ui.components.AuthTitle
import com.stslex.aproselection.feature.auth.ui.model.SnackbarActionType
import com.stslex.aproselection.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.UsernameTextFieldState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AuthScreen(
    state: AuthScreenState,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .swipeable(
                state = state.swipeableState,
                orientation = Orientation.Horizontal,
                anchors = mapOf(
                    screenWidth to AuthStore.AuthFieldsState.AUTH,
                    0f to AuthStore.AuthFieldsState.REGISTER
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        AuthScreenContent(state)
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = state.snackbarHostState
        ) { snackbarData ->
            when (SnackbarActionType.getByAction(snackbarData.visuals.actionLabel)) {
                SnackbarActionType.ERROR -> ErrorSnackbar(snackbarData)
                SnackbarActionType.SUCCESS -> SuccessSnackbar(snackbarData)
                SnackbarActionType.NONE -> return@SnackbarHost
            }
        }
    }
    if (state.screenLoadingState == AuthStore.ScreenLoadingState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthScreenContent(
    state: AuthScreenState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(AppDimens.Padding.big)
    ) {
        AuthTitle(
            modifier = Modifier.align(Alignment.TopCenter),
            swipeableState = state.swipeableState,
        )
        AuthFieldsColumn(
            modifier = Modifier.align(Alignment.Center),
            state = state
        )
    }
}

@Composable
fun AuthUsernameTextField(
    state: UsernameTextFieldState,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.text,
        onValueChange = state::onTextChange,
        singleLine = true,
        label = {
            Text(
                text = stringResource(id = R.string.auth_username_text)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        supportingText = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = state.supportingEndText
                )
            }
        },
    )
}

@Preview(
    device = "id:pixel_6", showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AuthScreenPreview() {
    AppTheme {
        AuthScreen(
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