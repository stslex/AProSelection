package com.stslex.aproselection.feature.auth.di

import com.stslex.aproselection.feature.auth.ui.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object FeatureAuthModule {

    val featureAuthModule = module {
        viewModelOf(::AuthViewModel)
    }
}