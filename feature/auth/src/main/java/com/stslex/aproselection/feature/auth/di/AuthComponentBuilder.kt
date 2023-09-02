package com.stslex.aproselection.feature.auth.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder
import com.stslex.aproselection.core.network.di.NetworkApiBuilder
import com.stslex.aproselection.core.ui.base.daggerViewModel
import com.stslex.aproselection.core.ui.di.NavigationApi
import com.stslex.aproselection.core.ui.di.navigationApi
import com.stslex.aproselection.feature.auth.ui.AuthViewModel

object AuthComponentBuilder {

    fun create(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): AuthComponent = DaggerAuthComponent
        .factory()
        .create(
            dependencies = DaggerAuthComponent_AuthComponentDependencies
                .factory()
                .create(
                    networkApi = NetworkApiBuilder.build(appApi),
                    appDataStoreApi = AppDataStoreBuilder.build(appApi),
                    navigatorApi = navigationApi
                )
        )
}

@Composable
fun setupComponent(): AuthViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        AuthComponentBuilder
            .create(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .viewModelFactory
    }
}
