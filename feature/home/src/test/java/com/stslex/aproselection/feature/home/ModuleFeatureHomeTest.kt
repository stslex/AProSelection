package com.stslex.aproselection.feature.home

import android.content.Context
import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.user.data.repository.UserRepository
import com.stslex.aproselection.feature.home.di.moduleFeatureHome
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class ModuleFeatureHomeTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            load<UserRepository>()
            load<AppDataStore>()
            modules(moduleFeatureHome)
            checkModules()
        }
    }

    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
    }
}