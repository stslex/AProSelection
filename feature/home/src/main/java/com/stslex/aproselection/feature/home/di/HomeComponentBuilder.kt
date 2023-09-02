package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder
import com.stslex.aproselection.core.navigation.di.NavigationComponentBuilder
import com.stslex.aproselection.core.user.di.UserCoreApiBuilder

object HomeComponentBuilder {

    fun build(appApi: AppApi): HomeComponent = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeComponentDependencies
                .factory()
                .create(
                    userCoreApi = UserCoreApiBuilder.build(appApi),
                    appDataStoreApi = AppDataStoreBuilder.build(appApi),
                    navigationApi = NavigationComponentBuilder.build()
                )
        )
}