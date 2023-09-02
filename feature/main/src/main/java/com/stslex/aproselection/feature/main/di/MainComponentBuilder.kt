package com.stslex.aproselection.feature.main.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.navigation.di.NavigationComponentBuilder

object MainComponentBuilder {

    fun build(
        appApi: AppApi,
    ): MainComponent = DaggerMainComponent
        .factory()
        .create(
            DaggerMainComponent_MainDependenciesComponent.factory()
                .create(
                    appApi = appApi,
                    navigationApi = NavigationComponentBuilder.build()
                )
        )
}