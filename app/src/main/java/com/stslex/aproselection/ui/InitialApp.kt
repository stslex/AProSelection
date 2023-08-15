package com.stslex.aproselection.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.destination.AppDestination
import com.stslex.aproselection.core.navigation.ext.NavExt.isAuth
import com.stslex.aproselection.navigation.NavigationHost
import com.stslex.aproselection.ui.components.AppToolbar
import com.stslex.aproselection.ui.components.menu_icon.MenuIconState
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitialApp(
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val viewModel: InitialAppViewModel = koinViewModel()
    val isInitialAuth by remember {
        viewModel.isInitialAuth
    }.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    var drawerState by remember {
        mutableStateOf(MenuIconState.CLOSE)
    }

    AppDestination
        .getStartDestination(isInitialAuth)
        ?.let { destination ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (navController.isAuth.not()) {
                    AppToolbar(
                        onClick = { drawerState = it }
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    NavigationHost(
                        navController = navController,
                        startDestination = destination
                    )
                    androidx.compose.animation.AnimatedVisibility(
                        visible = drawerState == MenuIconState.OPEN
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(screenWidth * 0.3f)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column {
                                for (index in 0..10) {
                                    Text(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        text = "item $index"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
}
