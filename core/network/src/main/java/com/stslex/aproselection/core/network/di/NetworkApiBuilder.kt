package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder

object NetworkApiBuilder {

    fun build(appApi: AppApi): NetworkApi = DaggerNetworkComponent
        .factory()
        .create(
            dependencies = DaggerNetworkComponent_NetworkComponentDependencies
                .factory()
                .create(
                    appDataStoreApi = AppDataStoreBuilder.build(appApi)
                )
        )
}