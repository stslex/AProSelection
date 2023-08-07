package com.stslex.aproselection

import android.content.Context
import androidx.navigation.NavHostController
import com.stslex.aproselection.core.datastore.coreDataStoreModule
import com.stslex.aproselection.core.navigation.di.moduleCoreNavigation
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.core.user.di.moduleCoreUser
import com.stslex.aproselection.di.appModule
import com.stslex.aproselection.di.initialAppModule
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import com.stslex.aproselection.feature.home.di.moduleFeatureHome
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        val navController = Mockito.mock(NavHostController::class.java)

        koinApplication {

            androidContext(Mockito.mock(Context::class.java))
            modules(
                moduleCoreNavigation(navController),
                initialAppModule,
                appModule,
                moduleCoreNetwork,
                moduleCoreUser,
                coreDataStoreModule,
                moduleFeatureAuth,
                moduleFeatureHome,
            )
            checkModules()
        }
    }
}