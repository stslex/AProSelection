package com.stslex.aproselection.feature.main.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.core.appApi
import com.stslex.aproselection.core.ui.base.daggerViewModel
import com.stslex.aproselection.core.ui.di.NavigationApi
import com.stslex.aproselection.feature.main.MainViewModel

object MainComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): MainComponent = DaggerMainComponent
        .factory()
        .create(
            DaggerMainComponent_MainDependenciesComponent.factory()
                .create(
                    appApi = appApi,
                    navigationApi = navigationApi
                )
        )
}

@Composable
fun setupComponent(
    navigationApi: NavigationApi
): MainViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        MainComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = navigationApi
            )
            .factory
    }
}
