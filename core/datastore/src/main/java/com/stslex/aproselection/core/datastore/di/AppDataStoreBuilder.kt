package com.stslex.aproselection.core.datastore.di

import com.stslex.aproselection.core.core.AppCoreApi

object AppDataStoreBuilder {

    fun build(
        appCoreApi: AppCoreApi
    ): AppDataStoreApi = DaggerAppDatastoreComponent.factory()
        .create(
            DaggerAppDatastoreComponent_AppDatastoreComponentDependencies
                .factory()
                .create(appCoreApi)
        )
}