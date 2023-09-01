package com.stslex.aproselection.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.ui.InitialAppViewModel
import com.stslex.aproselection.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(InitialAppViewModel::class)
    fun bindMainViewModel(impl: InitialAppViewModel): ViewModel

    @Binds
    fun bindViewModelProviderFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}