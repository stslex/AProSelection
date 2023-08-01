package com.stslex.aproselection.feature.auth.ui.model.mvi

import androidx.compose.runtime.Stable
import com.stslex.aproselection.feature.auth.ui.model.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState

@Stable
data class ScreenState(
    val screenLoadingState: ScreenLoadingState = ScreenLoadingState.Content,
    val username: String = "",
    val password: String = "",
    val passwordSubmit: String = "",
    val authFieldsState: AuthFieldsState = AuthFieldsState.AUTH
)
