package com.stslex.aproselection

import android.content.Context
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(
                moduleFeatureAuth,
                moduleCoreNetwork
            )
            checkModules()
        }
    }
}