package com.stslex.aproselection.di

import com.stslex.aproselection.ui.InitialAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InitialAppViewModel)
}