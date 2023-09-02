package com.stslex.aproselection.feature.auth.di

import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import com.stslex.aproselection.core.network.di.NetworkApi
import com.stslex.aproselection.core.ui.di.NavigationApi
import dagger.Component

@Component(
    modules = [AuthModule::class],
    dependencies = [AuthDependencies::class]
)
@AuthScope
interface AuthComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: AuthDependencies): AuthComponent
    }

    @Component(
        dependencies = [
            NetworkApi::class,
            AppDataStoreApi::class,
            NavigationApi::class
        ]
    )
    interface AuthComponentDependencies : AuthDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                networkApi: NetworkApi,
                appDataStoreApi: AppDataStoreApi,
                navigatorApi: NavigationApi
            ): AuthDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}