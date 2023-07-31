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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.feature.auth.ui.model.AuthState
import com.stslex.aproselection.feature.auth.ui.model.ScreenState

@Composable
fun AuthScreen(
    screenState: ScreenState,
    snackbarHostState: SnackbarHostState,
    auth: (String, String) -> Unit,
    register: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        AuthScreenContent(
            auth = auth,
            register = register
        )
    }
    if (screenState == ScreenState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    SnackbarHost(
        snackbarHostState
    ) { snackbarData ->
        Snackbar {
            Text(text = snackbarData.visuals.message)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreenContent(
    auth: (String, String) -> Unit,
    register: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var inputUsername by remember {
        mutableStateOf("")
    }

    var inputPassword by remember {
        mutableStateOf("")
    }

    var authState by remember {
        mutableStateOf(AuthState.AUTH)
    }

    val buttonRes by remember {
        derivedStateOf { authState.inverse.buttonResId }
    }

    val isFieldsValid by remember {
        derivedStateOf {
            inputPassword.length >= 8 && inputUsername.length >= 4
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthUsernameTextField(
            inputUsername = inputUsername,
            onTextChange = { username ->
                inputUsername = username
            }
        )
        Divider(Modifier.padding(16.dp))
        AuthPasswordTextField(
            inputPassword = inputPassword,
            onTextChange = { value ->
                inputPassword = value
            }
        )
        AnimatedVisibility(
            visible = authState == AuthState.REGISTER
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                AuthPasswordTextField(
                    inputPassword = inputPassword,
                    onTextChange = { value ->
                        inputPassword = value
                    }
                )
            }
        }
        Divider(Modifier.padding(16.dp))
        ElevatedButton(
            onClick = {
                keyboardController?.hide()
                when (authState) {
                    AuthState.REGISTER -> register(inputUsername, inputPassword)
                    AuthState.AUTH -> auth(inputUsername, inputPassword)
                }
            },
            enabled = isFieldsValid
        ) {
            Text(
                text = "submit",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Divider(Modifier.padding(16.dp))
        TextButton(
            onClick = {
                authState = authState.inverse
            }
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
        auth = { _, _ -> },
        register = { _, _ -> },
        screenState = ScreenState.Content,
        snackbarHostState = remember {
            SnackbarHostState()
        }
    )
}