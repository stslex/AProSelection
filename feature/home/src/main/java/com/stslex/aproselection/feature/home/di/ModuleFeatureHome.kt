package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.feature.home.domain.HomeInteractor
import com.stslex.aproselection.feature.home.domain.HomeInteractorImpl
import com.stslex.aproselection.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleFeatureHome = module {
    viewModelOf(::HomeViewModel)
    singleOf(::HomeInteractorImpl) { bind<HomeInteractor>() }
}