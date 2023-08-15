package com.stslex.aproselection.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.ui.components.menu_icon.AppDrawerState
import com.stslex.aproselection.ui.components.menu_icon.MenuIcon

@Composable
fun AppToolbar(
    drawerState: AppDrawerState,
    onClick: (AppDrawerState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .statusBarsPadding()
            .height(AppDimens.Size.toolbar)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "title",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        MenuIcon(
            modifier = Modifier
                .padding(AppDimens.Padding.medium)
                .align(Alignment.CenterStart),
            onClick = onClick,
            drawerState = drawerState,
            containerColorOpen = Color.Transparent
        )
    }
}

@Composable
@Preview
fun AppToolbarPreviewLight() {
    AppTheme {
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            AppToolbar(
                onClick = {},
                drawerState = AppDrawerState.OPEN
            )
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
            AppToolbar(
                onClick = {},
                drawerState = AppDrawerState.OPEN
            )
        }
    }
}