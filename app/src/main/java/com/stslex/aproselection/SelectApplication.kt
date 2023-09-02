package com.stslex.aproselection

import android.app.Application
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.core.AppCoreApi
import com.stslex.aproselection.core.core.ApplicationApi
import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.di.AppComponent
import com.stslex.aproselection.di.AppComponentBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
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

    private lateinit var dataStore: AppDataStore
    private lateinit var appController: AuthController

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
        coroutineScope.launch {
            appController.init()
            dataStore.init()
        }
    }
}