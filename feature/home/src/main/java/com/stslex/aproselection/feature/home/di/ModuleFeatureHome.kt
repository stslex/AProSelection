package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.feature.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val moduleFeatureHome = module {
    viewModelOf(::HomeViewModel)
}