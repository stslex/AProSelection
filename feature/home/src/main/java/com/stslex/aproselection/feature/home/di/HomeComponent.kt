package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import com.stslex.aproselection.core.user.di.UserCoreApi
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [HomeDependencies::class]
)
interface HomeComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: HomeDependencies): HomeComponent
    }

    @Component(dependencies = [UserCoreApi::class, AppDataStoreApi::class])
    interface HomeComponentDependencies : HomeDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                userCoreApi: UserCoreApi,
                appDataStoreApi: AppDataStoreApi
            ): HomeDependencies
        }
    }
}