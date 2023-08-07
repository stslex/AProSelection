package com.stslex.aproselection.core.navigation

import android.content.Context
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.di.moduleCoreNavigation
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class CoreNavigationModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        val navController = Mockito.mock(NavHostController::class.java)

        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(moduleCoreNavigation(navController))
            checkModules()
        }
    }
}