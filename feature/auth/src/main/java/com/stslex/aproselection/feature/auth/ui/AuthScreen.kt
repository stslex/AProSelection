package com.stslex.aproselection.feature.auth.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.components.ErrorSnackbar
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.components.AuthBottomText
import com.stslex.aproselection.feature.auth.ui.components.AuthFieldsColumn
import com.stslex.aproselection.feature.auth.ui.components.AuthTitle
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AuthScreen(
    state: AuthScreenState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        AuthScreenContent(state)
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = state.snackbarHostState
        ) { snackbarData ->
            ErrorSnackbar(snackbarData)
        }
    }
    if (state.screenLoadingState == ScreenLoadingState.Loading) {
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
            state = state.authFieldsState
        )
        AuthFieldsColumn(
            modifier = Modifier.align(Alignment.Center),
            state = state
        )
        AuthBottomText(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            authFieldsState = state.authFieldsState,
            onClick = state::onAuthFieldChange
        )
    }
}

@Composable
fun AuthUsernameTextField(
    inputUsername: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = inputUsername,
        onValueChange = { value ->
            if (inputUsername != value) {
                onTextChange(value)
            }
        },
        singleLine = true,
        label = {
            Text(text = "username")
        }
    )
}

@Composable
fun AuthPasswordTextField(
    inputPassword: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = inputPassword,
        onValueChange = { value ->
            if (inputPassword != value) {
                onTextChange(value)
            }
        },
        singleLine = true,
        supportingText = {
            Text(text = "enter password")
        },
        visualTransformation = PasswordVisualTransformation(),
        label = {
            Text(text = "password")
        }
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
                screenStateFlow = { MutableStateFlow(ScreenState()) },
                screenEventFlow = { MutableSharedFlow() },
                processAction = {}
            )
        )
    }
}