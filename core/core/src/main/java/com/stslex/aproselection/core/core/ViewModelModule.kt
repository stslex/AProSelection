package com.stslex.aproselection.core.core

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun viewModelProviderFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}