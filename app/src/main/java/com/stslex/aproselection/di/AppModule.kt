package com.stslex.aproselection.di

import com.stslex.aproselection.controller.AuthController
import com.stslex.aproselection.controller.AuthControllerImpl
import com.stslex.aproselection.ui.InitialAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AuthControllerImpl) { bind<AuthController>() }
}

val initialAppModule = module {
    viewModelOf(::InitialAppViewModel)
}