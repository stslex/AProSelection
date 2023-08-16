package com.stslex.aproselection.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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
        AppToolbarTitle(
            modifier = Modifier.align(Alignment.Center),
            text = if (drawerState == AppDrawerState.OPEN) "menu" else "long title"
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
fun AppToolbarTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    AnimatedText(
        modifier = modifier,
        text = text,
        textStyle = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    )
}

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
) {
    AnimatedContent(
        targetState = text,
        modifier = modifier.fillMaxWidth(),
        transitionSpec = animationSpec,
        label = "animatable text"
    ) { animatedText ->
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = animatedText,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

private val animationSpec: AnimatedContentTransitionScope<String>.() -> ContentTransform
    get() = {
        val time = 500
        val enterTransition = fadeIn(
            animationSpec = tween(
                durationMillis = time,
                easing = LinearEasing
            )
        ) + expandVertically(
            animationSpec = tween(
                durationMillis = time,
                easing = LinearEasing
            ),
            expandFrom = Alignment.CenterVertically
        )
        val exitTransition = fadeOut(
            animationSpec = tween(
                durationMillis = time,
                easing = LinearEasing
            )
        ) + shrinkVertically(
            animationSpec = tween(
                durationMillis = time,
                easing = LinearEasing
            ),
            shrinkTowards = Alignment.CenterVertically
        )
        enterTransition togetherWith exitTransition
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