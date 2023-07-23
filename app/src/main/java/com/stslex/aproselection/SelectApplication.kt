package com.stslex.aproselection

import android.app.Application
import com.stslex.aproselection.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.aproselection.feature.auth.di.ModuleFeatureAuth.moduleFeatureAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SelectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                moduleFeatureAuth,
                moduleCoreNetwork
            )
        }
    }
}