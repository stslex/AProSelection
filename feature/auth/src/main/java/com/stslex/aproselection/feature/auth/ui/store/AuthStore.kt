package com.stslex.aproselection.feature.auth.ui.store

import androidx.annotation.StringRes
import com.stslex.aproselection.core.ui.base.store.Store
import com.stslex.aproselection.feature.auth.R
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Event
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.State

interface AuthStore : Store<State, Event, Action> {

    data class State(
        val screenLoadingState: ScreenLoadingState,
        val username: String,
        val password: String,
        val passwordSubmit: String,
        val authFieldsState: AuthFieldsState
    ) : Store.State

    sealed interface Event : Store.Event {

        sealed interface ShowSnackbar : Event {

            data object SmthWentWrong : ShowSnackbar

            data object SuccessRegister : ShowSnackbar

            data class ApiErrorSnackbar(
                val message: String
            ) : ShowSnackbar
        }

        sealed interface Navigation : Event {

            data object AuthFeature : Navigation
        }
    }

    sealed interface Action : Store.Action {

        data object OnSubmitClicked : Action

        data class OnAuthFieldChange(
            val targetState: AuthFieldsState
        ) : Action

        sealed class InputAction(
            open val value: String
        ) : Action {

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

    enum class AuthFieldsState(
        @StringRes val buttonResId: Int,
        @StringRes val titleResId: Int
    ) {
        AUTH(
            buttonResId = R.string.auth_button_choose_log_in,
            titleResId = R.string.auth_title_auth
        ),
        REGISTER(
            buttonResId = R.string.auth_button_choose_register,
            titleResId = R.string.auth_title_register
        );
    }

    sealed interface ScreenLoadingState {

        data object Loading : ScreenLoadingState

        data object Content : ScreenLoadingState

        data class Error(
            val error: Throwable
        ) : ScreenLoadingState
    }
}