package com.stslex.aproselection.feature.main.di

import androidx.lifecycle.ViewModel
import com.stslex.aproselection.core.core.ViewModelKey
import com.stslex.aproselection.feature.main.InitialAppViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(InitialAppViewModel::class)
    fun bindMainViewModel(impl: InitialAppViewModel): ViewModel
}