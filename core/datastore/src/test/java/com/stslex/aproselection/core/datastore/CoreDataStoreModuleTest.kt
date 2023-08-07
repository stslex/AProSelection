package com.stslex.aproselection.core.datastore

import android.content.Context
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class CoreDataStoreModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {

        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(coreDataStoreModule)
            checkModules()
        }
    }
}