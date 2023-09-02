package com.stslex.aproselection.feature.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.core.ViewModelKey
import com.stslex.aproselection.feature.home.domain.HomeInteractor
import com.stslex.aproselection.feature.home.domain.HomeInteractorImpl
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStoreImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsViewModel(impl: HomeViewModel): ViewModel

    @Binds
    @HomeScope
    fun bindsHomeStore(impl: HomeScreenStoreImpl): HomeScreenStore

    @Binds
    @HomeScope
    fun bindsHomeInteractor(impl: HomeInteractorImpl): HomeInteractor

    @Binds
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}