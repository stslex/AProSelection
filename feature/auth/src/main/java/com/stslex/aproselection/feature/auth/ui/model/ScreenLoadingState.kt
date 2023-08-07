package com.stslex.aproselection.feature.auth.ui.model

sealed interface ScreenLoadingState {

    data object Loading : ScreenLoadingState

    data object Content : ScreenLoadingState

    data class Error(
        val error: Throwable
    ) : ScreenLoadingState
}
