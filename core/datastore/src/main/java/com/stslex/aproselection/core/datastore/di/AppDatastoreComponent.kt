package com.stslex.aproselection.core.datastore.di

import com.stslex.aproselection.core.core.AppCoreApi
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppDataStoreModule::class],
    dependencies = [AppDatastoreDependencies::class]
)
@Singleton
interface AppDatastoreComponent : AppDataStoreApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppDatastoreDependencies): AppDatastoreComponent
    }

    @Component(dependencies = [AppCoreApi::class])
    interface AppDatastoreComponentDependencies : AppDatastoreDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                appCoreApi: AppCoreApi
            ): AppDatastoreComponentDependencies
        }
    }
}