package com.stslex.aproselection.ui.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.navigation.di.NavigationApi
import com.stslex.aproselection.core.navigation.di.NavigationComponentBuilder

object MainComponentBuilder {

    fun build(
        appApi: AppApi,
    ): MainComponent = DaggerMainComponent.factory().create(
        DaggerMainComponent_MainDependenciesComponent.factory().create(appApi, NavigationComponentBuilder.build())
    )
}