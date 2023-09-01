package com.stslex.aproselection.di

import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.controller.AuthControllerImpl
import com.stslex.aproselection.ui.InitialAppViewModel
import dagger.Binds
import dagger.Module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@Module
interface AppModule {

    @Binds
    fun bind(impl: AuthControllerImpl): AuthController
}

val appModule = module {
    singleOf(::AuthControllerImpl) { bind<AuthController>() }
}

val initialAppModule = module {
    viewModelOf(::InitialAppViewModel)
}