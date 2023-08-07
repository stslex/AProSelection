package com.stslex.aproselection.feature.auth.ui.model.mvi

interface ScreenEvent {

    data class Error(
        val error: Throwable
    ) : ScreenEvent

    data object SuccessRegistered : ScreenEvent
}