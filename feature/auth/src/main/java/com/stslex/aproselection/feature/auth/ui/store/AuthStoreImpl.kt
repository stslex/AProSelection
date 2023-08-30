package com.stslex.aproselection.feature.auth.ui.store

import com.stslex.aproselection.core.network.model.ApiError
import com.stslex.aproselection.core.ui.base.store.BaseStoreImpl
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Event
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthStoreImpl(
    private val interactor: AuthInteractor
) : AuthStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        screenLoadingState = ScreenLoadingState.Content,
        username = "",
        password = "",
        passwordSubmit = "",
        authFieldsState = AuthFieldsState.AUTH
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.InputAction.UsernameInput -> processUsernameInput(action)
            is Action.OnSubmitClicked -> processSubmitClicked()
            is Action.InputAction.PasswordInput -> processPasswordInput(action)
            is Action.InputAction.PasswordSubmitInput -> processPasswordSubmitInput(action)
            is Action.OnAuthFieldChange -> processAuthFieldChange(action)
        }
    }

    private fun processUsernameInput(action: Action.InputAction.UsernameInput) {
        updateState { currentValue ->
            currentValue.copy(
                username = action.value
            )
        }
    }

    private fun processPasswordInput(action: Action.InputAction.PasswordInput) {
        updateState { currentValue ->
            currentValue.copy(
                password = action.value
            )
        }
    }

    private fun processPasswordSubmitInput(action: Action.InputAction.PasswordSubmitInput) {
        updateState { currentValue ->
            currentValue.copy(
                passwordSubmit = action.value,
            )
        }
    }

    private fun processAuthFieldChange(action: Action.OnAuthFieldChange) {
        updateState { currentValue ->
            currentValue.copy(
                authFieldsState = action.targetState
            )
        }
    }

    private fun processSubmitClicked() {
        when (state.value.authFieldsState) {
            AuthFieldsState.AUTH -> auth()
            AuthFieldsState.REGISTER -> register()
        }
    }

    private fun register() {
        val state = state.value
        setLoadingState(ScreenLoadingState.Loading)

        scope.launch(Dispatchers.IO) {
            runCatching {
                interactor
                    .register(
                        username = state.username,
                        password = state.password
                    )
            }
                .onFailure { throwable ->
                    onError(throwable)
                    setLoadingState(ScreenLoadingState.Content)
                    logError(throwable)
                }
                .onSuccess {
                    updateState { currentState ->
                        currentState.copy(
                            screenLoadingState = ScreenLoadingState.Content,
                            authFieldsState = AuthFieldsState.AUTH
                        )
                    }
                    sendEvent(Event.ShowSnackbar.SuccessRegister)
                }
        }
    }

    private fun auth() {
        val state = state.value
        setLoadingState(ScreenLoadingState.Loading)
        interactor
            .auth(
                username = state.username,
                password = state.password
            )
            .catch { throwable ->
                onError(throwable)
                setLoadingState(ScreenLoadingState.Content)
            }
            .onEach {
                setLoadingState(ScreenLoadingState.Content)
                sendEvent(Event.Navigation.AuthFeature)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(scope)
    }

    private fun onError(throwable: Throwable) {
        val event = if (throwable is ApiError) {
            Event.ShowSnackbar.ApiErrorSnackbar(throwable.message)
        } else {
            Event.ShowSnackbar.SmthWentWrong
        }
        sendEvent(event)
        logError(throwable)
    }


    private fun setLoadingState(screenLoadingState: ScreenLoadingState) {
        updateState { state ->
            state.copy(
                screenLoadingState = screenLoadingState
            )
        }
    }
}