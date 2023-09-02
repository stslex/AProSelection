package com.stslex.aproselection.feature.home.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreBuilder
import com.stslex.aproselection.core.ui.base.daggerViewModel
import com.stslex.aproselection.core.ui.di.NavigationApi
import com.stslex.aproselection.core.ui.di.navigationApi
import com.stslex.aproselection.core.user.di.UserCoreApiBuilder
import com.stslex.aproselection.feature.home.ui.HomeViewModel

object HomeComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): HomeComponent = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeComponentDependencies
                .factory()
                .create(
                    userCoreApi = UserCoreApiBuilder.build(appApi),
                    appDataStoreApi = AppDataStoreBuilder.build(appApi),
                    navigationApi = navigationApi
                )
        )
}

@Composable
fun setupComponent(): HomeViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        HomeComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .getViewModelFactory()
    }
}