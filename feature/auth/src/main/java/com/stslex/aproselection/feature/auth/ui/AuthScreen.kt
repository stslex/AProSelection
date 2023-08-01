package com.stslex.aproselection.feature.auth.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.AuthScreenState
import com.stslex.aproselection.feature.auth.ui.model.screen.rememberAuthScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

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
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            hostState = state.snackbarHostState
        ) { snackbarData ->
            Snackbar {
                Text(text = snackbarData.visuals.message)
            }
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

    val buttonRes by remember {
        derivedStateOf { state.authFieldsState.inverse.buttonResId }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthUsernameTextField(
            inputUsername = state.username,
            onTextChange = state::onUsernameChange
        )
        Divider(Modifier.padding(16.dp))
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
                Spacer(modifier = Modifier.height(8.dp))
                AuthPasswordTextField(
                    inputPassword = state.passwordSubmit,
                    onTextChange = state::onPasswordSubmitChange
                )
            }
        }
        Divider(Modifier.padding(16.dp))
        ElevatedButton(
            onClick = state::onSubmitClicked,
            enabled = state.isFieldsValid
        ) {
            Text(
                text = "submit",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Divider(Modifier.padding(16.dp))
        TextButton(
            onClick = state::onAuthFieldChange
        ) {
            Text(
                text = stringResource(id = buttonRes),
                style = MaterialTheme.typography.titleMedium
            )
        }
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

@Preview(device = "id:pixel_6", showSystemUi = true, showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        state = rememberAuthScreenState(
            screenStateFlow = { MutableStateFlow(ScreenState()) },
            screenEventFlow = { MutableSharedFlow() },
            processAction = {}
        )
    )
}