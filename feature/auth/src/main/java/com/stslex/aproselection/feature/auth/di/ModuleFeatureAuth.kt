package com.stslex.aproselection.feature.auth.di

import com.stslex.aproselection.feature.auth.data.repository.AuthRepository
import com.stslex.aproselection.feature.auth.data.repository.AuthRepositoryImpl
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractorImpl
import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import com.stslex.aproselection.feature.auth.ui.store.AuthStore
import com.stslex.aproselection.feature.auth.ui.store.AuthStoreImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object ModuleFeatureAuth {

    val moduleFeatureAuth = module {
        viewModelOf(::AuthViewModel)
        factoryOf(::AuthStoreImpl) { bind<AuthStore>() }
        factoryOf(::AuthInteractorImpl) { bind<AuthInteractor>() }
        factoryOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    }
}