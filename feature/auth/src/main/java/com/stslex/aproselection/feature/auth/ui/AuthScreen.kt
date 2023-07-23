package com.stslex.aproselection.feature.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.navigation.NavigationScreen

@Composable
fun AuthScreen(
    text: String,
    navigate: (NavigationScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        text = "text",
        navigate = {}
    )
}