package com.stslex.aproselection.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.ui.components.menu_icon.MenuIcon
import com.stslex.aproselection.ui.components.menu_icon.MenuIconState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    onClick: (MenuIconState) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "title")
        },
        navigationIcon = {
            MenuIcon(onClick = onClick)
        }
    )
}

@Composable
@Preview
fun AppToolbarPreviewLight() {
    AppTheme {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            AppToolbar(onClick = {})
        }
    }
}

@Composable
@Preview
fun AppToolbarPreviewDark() {
    AppTheme(true) {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            AppToolbar(onClick = {})
        }
    }
}