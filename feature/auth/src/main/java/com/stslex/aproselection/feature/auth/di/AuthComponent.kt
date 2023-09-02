package com.stslex.aproselection.feature.auth.di

import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import com.stslex.aproselection.core.network.di.NetworkApi
import dagger.Component

@Component(
    modules = [AuthModule::class],
    dependencies = [AuthDependencies::class]
)
interface AuthComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthDependencies): AuthComponent
    }

    @Component(dependencies = [NetworkApi::class, AppDataStoreApi::class])
    interface AuthComponentDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                networkApi: NetworkApi,
                appDataStoreApi: AppDataStoreApi
            ): AuthComponentDependencies
        }
    }
}