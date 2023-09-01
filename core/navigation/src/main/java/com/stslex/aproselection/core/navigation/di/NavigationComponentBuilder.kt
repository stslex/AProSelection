package com.stslex.aproselection.core.navigation.di

object NavigationComponentBuilder {

    fun build(

    ): NavigationApi = DaggerNavigationComponent.factory()
        .create(object : NavigationDependencies{})
}