package com.stslex.aproselection.feature.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.core.ViewModelKey
import com.stslex.aproselection.core.ui.base.ViewModelFactory
import com.stslex.aproselection.feature.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}