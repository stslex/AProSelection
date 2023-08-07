package com.stslex.aproselection.core.user

import android.content.Context
import com.stslex.aproselection.core.network.clients.user.UserNetworkClient
import com.stslex.aproselection.core.user.di.moduleCoreUser
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class CoreUserModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            load<UserNetworkClient>()
            modules(moduleCoreUser)
            checkModules()
        }
    }

    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
    }
}