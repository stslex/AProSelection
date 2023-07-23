package com.stslex.aproselection.core.network

import android.content.Context
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
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
            modules(moduleCoreNetwork)
            checkModules()
        }
    }
}