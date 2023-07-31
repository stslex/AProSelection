package com.stslex.aproselection.feature.auth.ui.model

interface ScreenEvent {

    data class Error(
        val throwable: Throwable
    ) : ScreenEvent
}