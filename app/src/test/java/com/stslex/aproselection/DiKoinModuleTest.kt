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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class DiKoinModuleTest : KoinTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun checkKoinModules() {
        val navController = Mockito.mock(NavHostController::class.java)

        koinApplication {
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
            checkModules {
                withInstance<Context>()
            }
        }
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}