package com.stslex.aproselection.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.main.di.MainComponentBuilder
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: InitialAppViewModel by viewModels {
        viewModelProvider
    }
    private val mainComponent by lazy {
        MainComponentBuilder.build(appApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
//            val navigationApi = DaggerNavigationComponent.factory()
//                .create(object : NavigationDependencies {
//                    override val hostController: NavHostController
//                        get() = navHostController
//                })
//            DaggerMainComponent.builder().navigationApi(navigationApi).build().inject(this)
            val isInitialAuth by remember {
                viewModel.isInitialAuth
            }.collectAsState()
            AppTheme {
                InitialApp(
                    navController = navHostController,
                    isInitialAuth = isInitialAuth
                )
                LaunchedEffect(Unit) {
                    viewModel.init()
                }
            }
        }
    }
}
