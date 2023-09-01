package com.stslex.aproselection.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.navigation.di.moduleCoreNavigation
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.di.initialAppModule
import com.stslex.aproselection.ui.di.MainComponentBuilder
import org.koin.androidx.compose.getKoin
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private val viewModel: InitialAppViewModel by viewModels {
        viewModelProvider
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainComponentBuilder.build(appApi).inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
//            val navigationApi = DaggerNavigationComponent.factory()
//                .create(object : NavigationDependencies {
//                    override val hostController: NavHostController
//                        get() = navHostController
//                })
//            DaggerMainComponent.builder().navigationApi(navigationApi).build().inject(this)
            SetupComposeDependencies(navHostController)
            AppTheme {
                InitialApp(
                    navController = navHostController
                )
            }
        }
    }

    @Composable
    private fun SetupComposeDependencies(
        navController: NavHostController
    ) {
        getKoin().loadModules(
            listOf(
                moduleCoreNavigation(navController),
                initialAppModule
            )
        )
    }
}
