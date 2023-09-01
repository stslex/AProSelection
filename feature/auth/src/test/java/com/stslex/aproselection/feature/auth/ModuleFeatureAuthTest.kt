package com.stslex.aproselection.feature.auth

import android.content.Context
import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class ModuleFeatureAuthTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            load<Navigator>()
            load<AuthNetworkClient>()
            load<AppDataStore>()
            modules(moduleFeatureAuth)
            checkModules()
        }
    }

    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
    }
}