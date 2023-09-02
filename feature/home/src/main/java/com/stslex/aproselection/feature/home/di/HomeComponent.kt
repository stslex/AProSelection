package com.stslex.aproselection.feature.home.di

import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import com.stslex.aproselection.core.navigation.di.NavigationApi
import com.stslex.aproselection.core.user.di.UserCoreApi
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [HomeDependencies::class]
)
@HomeScope
interface HomeComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: HomeDependencies): HomeComponent
    }

    @Component(
        dependencies = [
            UserCoreApi::class,
            AppDataStoreApi::class,
            NavigationApi::class
        ]
    )
    interface HomeComponentDependencies : HomeDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                userCoreApi: UserCoreApi,
                appDataStoreApi: AppDataStoreApi,
                navigationApi: NavigationApi
            ): HomeDependencies
        }
    }

    fun getViewModelFactory(): ViewModelProvider.Factory
}