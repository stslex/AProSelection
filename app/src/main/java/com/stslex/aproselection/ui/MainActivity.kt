package com.stslex.aproselection.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.di.navigationModule
import org.koin.androidx.compose.getKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
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
        val navModule = navigationModule(navController)
        getKoin().loadModules(
            listOf(navModule)
        )
    }
}
