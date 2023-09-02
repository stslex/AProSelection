package com.stslex.aproselection.core.navigation.di

import com.stslex.aproselection.core.navigation.navigator.NavigatorImpl
import com.stslex.aproselection.core.ui.di.Navigator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigator(impl: NavigatorImpl): Navigator
}