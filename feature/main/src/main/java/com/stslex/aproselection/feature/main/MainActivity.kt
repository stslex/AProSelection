package com.stslex.aproselection.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.navigation.di.NavigationComponentBuilder
import com.stslex.aproselection.core.ui.di.MainUiApi
import com.stslex.aproselection.core.ui.di.NavigationApi
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.main.di.setupComponent

class MainActivity : ComponentActivity(), MainUiApi {

    private lateinit var navApi: NavigationApi
    override val navigationApi: NavigationApi
        get() = navApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            navApi = remember(navHostController) {
                NavigationComponentBuilder.build(navHostController)
            }
            val viewModel = setupComponent(navigationApi)
            AppTheme {
                InitialApp(
                    navController = navHostController,
                    viewModel = viewModel
                )
            }
        }
    }
}
