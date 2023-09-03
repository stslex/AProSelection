package com.stslex.aproselection.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.ui.di.NavigationApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface NavigationComponent : NavigationApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun controller(navHostController: NavHostController): Builder

        fun build(): NavigationApi
    }
}

