package com.stslex.aproselection.feature.auth.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder
import com.stslex.aproselection.core.navigation.di.NavigationComponentBuilder
import com.stslex.aproselection.core.network.di.NetworkApiBuilder

object AuthComponentBuilder {

    fun create(
        appApi: AppApi
    ): AuthComponent = DaggerAuthComponent
        .factory()
        .create(
            dependencies = DaggerAuthComponent_AuthComponentDependencies
                .factory()
                .create(
                    networkApi = NetworkApiBuilder.build(appApi),
                    appDataStoreApi = AppDataStoreBuilder.build(appApi),
                    navigatorApi = NavigationComponentBuilder.build()
                )
        )
}
