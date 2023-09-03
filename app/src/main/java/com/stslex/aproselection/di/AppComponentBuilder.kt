package com.stslex.aproselection.di

import android.content.Context
import com.stslex.aproselection.core.core.AppCoreApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder
import com.stslex.aproselection.di.core.DaggerAppCoreComponent

object AppComponentBuilder {

    fun build(
        appCoreApi: AppCoreApi
    ): AppComponent = DaggerAppComponent
        .factory()
        .create(
            appApi = appCoreApi,
            dataStoreApi = AppDataStoreBuilder.build(appCoreApi)
        )

    fun build(
        context: Context
    ): AppCoreApi = DaggerAppCoreComponent
        .builder()
        .context(context)
        .build()
}