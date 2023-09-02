package com.stslex.aproselection.feature.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.core.ViewModelKey
import com.stslex.aproselection.core.ui.base.ViewModelFactory
import com.stslex.aproselection.feature.auth.data.repository.AuthRepository
import com.stslex.aproselection.feature.auth.data.repository.AuthRepositoryImpl
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractorImpl
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import com.stslex.aproselection.feature.auth.ui.store.AuthStore
import com.stslex.aproselection.feature.auth.ui.store.AuthStoreImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindsViewModel(impl: AuthViewModel): ViewModel

    @Binds
    @AuthScope
    fun bindsAuthInteractor(impl: AuthInteractorImpl): AuthInteractor

    @Binds
    @AuthScope
    fun bindsRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @AuthScope
    fun bindStore(impl: AuthStoreImpl): AuthStore

    @Binds
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}