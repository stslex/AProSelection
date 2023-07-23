package com.stslex.aproselection.feature.auth

import android.content.Context
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class ModuleFeatureAuthTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(ModuleFeatureAuth.moduleFeatureAuth)
            checkModules()
        }
    }
}