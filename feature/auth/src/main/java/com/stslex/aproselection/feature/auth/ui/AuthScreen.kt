package com.stslex.aproselection.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.navigation.NavigationScreen

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

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = inputUsername,
                onValueChange = { value ->
                    if (inputUsername != value) {
                        inputUsername = value
                    }
                },
                maxLines = 1,
            )
            Divider(Modifier.padding(16.dp))
            TextField(
                value = inputPassword,
                onValueChange = { value ->
                    if (inputPassword != value) {
                        inputPassword = value
                    }
                },
                maxLines = 1,
            )
            Divider(Modifier.padding(16.dp))
            ElevatedButton(
                onClick = {
                    auth(inputUsername, inputPassword)
                    inputUsername = ""
                }
            ) {
                Text(
                    text = "submit",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Divider(Modifier.padding(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

//@Preview(device = "id:pixel_6", showSystemUi = true, showBackground = true)
//@Composable
//fun AuthScreenPreview() {
//    AuthScreen(
//        text = "text",
//        navigate = {},
//        setUsername = {}
//    )
//}