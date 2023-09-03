package com.stslex.aproselection.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.ui.di.NavigationApi

object NavigationComponentBuilder {

    fun build(
        navHostController: NavHostController
    ): NavigationApi = DaggerNavigationComponent
        .builder()
        .controller(navHostController)
        .build()
}