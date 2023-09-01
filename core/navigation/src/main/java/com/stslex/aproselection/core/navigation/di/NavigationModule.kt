package com.stslex.aproselection.core.navigation.di

import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.navigation.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun bindNavigator(impl: NavigatorImpl): Navigator
}