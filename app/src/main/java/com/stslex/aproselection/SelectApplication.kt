package com.stslex.aproselection

import android.app.Application
import com.stslex.aproselection.feature.auth.di.FeatureAuthModule.featureAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SelectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(featureAuthModule)
        }
    }
}