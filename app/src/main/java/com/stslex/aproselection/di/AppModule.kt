package com.stslex.aproselection.di

import androidx.navigation.NavHostController
import com.stslex.aproselection.controller.AuthController
import com.stslex.aproselection.controller.AuthControllerImpl
import com.stslex.aproselection.core.ui.navigation.navigator.Navigator
import com.stslex.aproselection.core.ui.navigation.navigator.NavigatorImpl
import com.stslex.aproselection.ui.InitialAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AuthControllerImpl) { bind<AuthController>() }
}

fun navigationModule(
    navHostController: NavHostController
) = module {
    single<Navigator> { NavigatorImpl(navHostController) }
    viewModelOf(::InitialAppViewModel)
}