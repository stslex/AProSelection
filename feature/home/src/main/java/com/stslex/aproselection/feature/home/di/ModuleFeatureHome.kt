package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.feature.home.domain.HomeInteractor
import com.stslex.aproselection.feature.home.domain.HomeInteractorImpl
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStoreImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moduleFeatureHome = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::HomeScreenStoreImpl) { bind<HomeScreenStore>() }
    factoryOf(::HomeInteractorImpl) { bind<HomeInteractor>() }
}