package com.stslex.aproselection

import android.app.Application
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.core.AppCoreApi
import com.stslex.aproselection.core.core.ApplicationApi
import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.core.datastore.coreDataStoreModule
import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.core.user.di.moduleCoreUser
import com.stslex.aproselection.di.AppComponent
import com.stslex.aproselection.di.AppComponentBuilder
import com.stslex.aproselection.di.appModule
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import com.stslex.aproselection.feature.home.di.moduleFeatureHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import javax.inject.Inject

class SelectApplication : Application(), ApplicationApi {

    private val appCoreApi: AppCoreApi by lazy {
        AppComponentBuilder.build(this)
    }

    private val appComponent: AppComponent by lazy {
        AppComponentBuilder.build(appCoreApi)
    }

    override val appApi: AppApi
        get() = appComponent

    lateinit var dataStore: AppDataStore
    lateinit var appController: AuthController

    @Inject
    fun initDependencies(
        dataStore: AppDataStore,
        appController: AuthController
    ) {
        this.dataStore = dataStore
        this.appController = appController
    }

    private val coroutineScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
        setupDependencies()
        initControllers()
    }

    private fun initControllers() {
        coroutineScope.launch {
            appController.init()
            dataStore.init()
        }
    }

    private fun setupDependencies() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                appModule,
                moduleCoreNetwork,
                moduleCoreUser,
                coreDataStoreModule,
                moduleFeatureAuth,
                moduleFeatureHome,
            )
        }
    }
}