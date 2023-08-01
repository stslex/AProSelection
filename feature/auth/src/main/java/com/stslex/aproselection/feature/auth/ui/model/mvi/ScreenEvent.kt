package com.stslex.aproselection.feature.auth.ui.model.mvi

import com.stslex.aproselection.feature.auth.ui.model.ErrorType

interface ScreenEvent {

    data class Error(
        val errorType: ErrorType
    ) : ScreenEvent
}