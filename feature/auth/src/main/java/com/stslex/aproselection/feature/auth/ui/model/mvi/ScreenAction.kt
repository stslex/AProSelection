package com.stslex.aproselection.feature.auth.ui.model.mvi

sealed interface ScreenAction {

    data class UsernameInput(
        val value: String
    ) : ScreenAction

    data class PasswordInput(
        val value: String
    ) : ScreenAction

    data class PasswordSubmitInput(
        val value: String
    ) : ScreenAction

    data object OnSubmitClicked : ScreenAction

    data object OnAuthFieldChange : ScreenAction
}