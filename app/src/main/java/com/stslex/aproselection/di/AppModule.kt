package com.stslex.aproselection.di

import com.stslex.aproselection.controller.AuthControllerImpl
import com.stslex.aproselection.core.core.AuthController
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun bindAuthController(impl: AuthControllerImpl): AuthController
}