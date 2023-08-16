package com.stslex.aproselection.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.core.navigation.ext.NavExt.isAuth
import com.stslex.aproselection.core.ui.ext.noRippleClick
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.navigation.NavigationHost
import com.stslex.aproselection.ui.components.AppToolbar
import com.stslex.aproselection.ui.components.menu_icon.AppDrawerState
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitialApp(
    navController: NavHostController
) {
    val viewModel: InitialAppViewModel = koinViewModel()
    val isInitialAuth by remember {
        viewModel.isInitialAuth
    }.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    AppDestination
        .getStartDestination(isInitialAuth)
        ?.let { destination ->
            AppContainer(
                isAuth = navController.isAuth
            ) {
                NavigationHost(
                    navController = navController,
                    startDestination = destination
                )
            }
        }
}

@Composable
fun AppContainer(
    isAuth: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var drawerState by remember {
        mutableStateOf(AppDrawerState.CLOSE)
    }

    val drawerSlide by animateDpAsState(
        targetValue = when (drawerState) {
            AppDrawerState.OPEN -> 0.dp
            AppDrawerState.CLOSE -> -screenWidth * 0.3f
        },
        animationSpec = tween(500)
    )

    BackHandler(
        enabled = drawerState == AppDrawerState.OPEN
    ) {
        drawerState = AppDrawerState.CLOSE
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = if (isAuth) AppDimens.Size.toolbar else 0.dp),
            content = content
        )

        AnimatedVisibility(
            visible = drawerState == AppDrawerState.OPEN,
            enter = fadeIn(tween(500)),
            exit = fadeOut(tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClick {
                        drawerState = AppDrawerState.CLOSE
                    }
                    .background(
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(screenWidth * 0.3f)
                .offset(x = drawerSlide)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = AppDimens.Corners.medium,
                        bottomEnd = AppDimens.Corners.medium,
                    )
                )
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .statusBarsPadding()
                .noRippleClick()
                .padding(
                    top = AppDimens.Size.toolbar,
                    start = AppDimens.Padding.medium,
                )
        ) {
            Column {
                for (index in 0..10) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp),
                        text = "item $index",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (isAuth) {
            AppToolbar(
                onClick = { drawerState = it },
                drawerState = drawerState
            )
        }
    }
}
