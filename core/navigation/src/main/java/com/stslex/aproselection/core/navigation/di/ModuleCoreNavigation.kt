package com.stslex.aproselection.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.navigation.navigator.NavigatorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val moduleCoreNavigation: (navHostController: NavHostController) -> Module = { navHostController ->
    module {
        single<Navigator> {
            NavigatorImpl(
//                navHostController
            )
        }
    }
}