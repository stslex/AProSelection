package com.stslex.aproselection.core.navigation.di

import dagger.Component

@Component(
    modules = [NavigationModule::class],
    dependencies = [NavigationDependencies::class]
)
interface NavigationComponent : NavigationApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: NavigationDependencies): NavigationApi
    }
}

