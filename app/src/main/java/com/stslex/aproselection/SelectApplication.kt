package com.stslex.aproselection

import android.app.Application
import com.stslex.aproselection.controller.AuthController
import com.stslex.aproselection.core.datastore.coreDataStoreModule
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.di.appModule
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import com.stslex.aproselection.feature.home.di.moduleFeatureHome
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SelectApplication : Application() {

    private val appController: AuthController by inject()

    override fun onCreate() {
        super.onCreate()
        setupDependencies()
        initControllers()
    }

    private fun initControllers() {
        appController.init()
    }

    private fun setupDependencies() {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                appModule,
                moduleCoreNetwork,
                coreDataStoreModule,
                moduleFeatureAuth,
                moduleFeatureHome,
            )
        }
    }
}