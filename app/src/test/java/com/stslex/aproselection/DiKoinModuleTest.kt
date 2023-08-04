package com.stslex.aproselection

import android.content.Context
import com.stslex.aproselection.core.datastore.coreDataStoreModule
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.core.navigation.destination.NavigationScreen
import com.stslex.aproselection.di.appModule
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import com.stslex.aproselection.feature.home.di.moduleFeatureHome
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        val navigator: (screen: com.stslex.aproselection.core.navigation.destination.NavigationScreen) -> Unit = {}

        koinApplication {

            androidContext(Mockito.mock(Context::class.java))
            modules(
                module {
                    single {
                        navigator
                    }
                },
                appModule,
                moduleCoreNetwork,
                coreDataStoreModule,
                moduleFeatureAuth,
                moduleFeatureHome,
            )
            checkModules()
        }
    }
}