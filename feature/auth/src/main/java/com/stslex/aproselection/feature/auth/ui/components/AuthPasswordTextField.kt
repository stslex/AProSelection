package com.stslex.aproselection.feature.auth.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.model.screen.text_field.PasswordTextFieldState

@Composable
fun AuthPasswordTextField(
    state: PasswordTextFieldState,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = state.text,
        onValueChange = state::onTextChange,
        singleLine = true,
        supportingText = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = stringResource(id = state.hint)
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = state.supportingEndText
                )
            }
        },
        visualTransformation = state.visualTransformation.value,
        label = {
            Text(text = stringResource(state.label))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            IconButton(
                onClick = state::onPasswordHideClicked
            ) {
                Icon(
                    painter = painterResource(id = state.trailingIconRes.value),
                    contentDescription = null
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
fun AuthPasswordTextFieldPreview() {
    AppTheme {
        var text by remember {
            mutableStateOf("text")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
//            AuthPasswordTextField(
//                modifier = Modifier.align(Alignment.Center),
//                inputPassword = text,
//                onTextChange = { value ->
//                    text = value
//                },
//                hint = "enterPassword",
//                supportingEndText = "4/10"
//            )
        }
    }
}