package com.stslex.aproselection.feature.auth.ui.model

sealed interface ScreenState {

    object Loading : ScreenState

    object Content : ScreenState
}