package com.stslex.aproselection.feature.auth.ui.model

sealed interface ScreenLoadingState {

    data object Loading : ScreenLoadingState

    data object Content : ScreenLoadingState

    data class Error(
        val error: ErrorType
    ) : ScreenLoadingState
}

sealed interface ErrorType {

    sealed interface Api : ErrorType

    data class UnResolve(
        val throwable: Throwable
    ) : ErrorType
}