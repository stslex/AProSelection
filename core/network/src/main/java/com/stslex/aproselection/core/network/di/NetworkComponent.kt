package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import dagger.Component

@Component(
    modules = [NetworkModule::class],
    dependencies = [NetworkDependencies::class]
)
interface NetworkComponent : NetworkApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NetworkDependencies): NetworkComponent
    }

    @Component(dependencies = [AppDataStoreApi::class])
    interface NetworkComponentDependencies : NetworkDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                appDataStoreApi: AppDataStoreApi
            ): NetworkDependencies
        }
    }
}