package com.stslex.aproselection.feature.auth.ui.model.mvi

sealed interface ScreenAction {

    data object OnSubmitClicked : ScreenAction

    data object OnAuthFieldChange : ScreenAction

    sealed class InputAction(
        open val value: String
    ) : ScreenAction {

        data class UsernameInput(
            override val value: String
        ) : InputAction(value)

        data class PasswordInput(
            override val value: String
        ) : InputAction(value)

        data class PasswordSubmitInput(
            override val value: String
        ) : InputAction(value)
    }
}