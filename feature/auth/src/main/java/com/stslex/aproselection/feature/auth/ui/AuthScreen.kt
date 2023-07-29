package com.stslex.aproselection.feature.auth.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import com.stslex.aproselection.feature.auth.ui.model.AuthState

@Composable
fun AuthScreen(
    text: String,
    navigate: (NavigationScreen) -> Unit,
    auth: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var inputUsername by remember {
        mutableStateOf("")
    }

    var inputPassword by remember {
        mutableStateOf("")
    }

    var authState by remember {
        mutableStateOf(AuthState.REGISTER)
    }

    val buttonRes by remember {
        derivedStateOf { authState.buttonResId }
    }

    val isFieldsValid by remember {
        derivedStateOf {
            inputPassword.length >= 8 && inputUsername.length >= 8
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {

        Column(
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
                    auth(inputUsername, inputPassword)
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
                    authState = authState.onClick()
                }
            ) {
                Text(
                    text = stringResource(id = buttonRes),
                    style = MaterialTheme.typography.titleMedium
                )
            }
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
        text = "text",
        navigate = {},
        auth = { _, _ -> }
    )
}