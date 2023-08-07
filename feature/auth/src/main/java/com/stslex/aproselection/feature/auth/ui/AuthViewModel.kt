package com.stslex.aproselection.feature.auth.ui

import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import com.stslex.aproselection.feature.auth.ui.model.AuthFieldsState
import com.stslex.aproselection.feature.auth.ui.model.ScreenLoadingState
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.PasswordInput
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.PasswordSubmitInput
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenAction.InputAction.UsernameInput
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenEvent
import com.stslex.aproselection.feature.auth.ui.model.mvi.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class AuthViewModel(
    private val interactor: AuthInteractor,
    private val navigator: Navigator
) : BaseViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState>
        get() = _screenState.asStateFlow()

    private val _screenEvents: MutableSharedFlow<ScreenEvent> = MutableSharedFlow()
    val screenEvent: SharedFlow<ScreenEvent>
        get() = _screenEvents.asSharedFlow()

    fun process(action: ScreenAction) {
        when (action) {
            is UsernameInput -> processUsernameInput(action)
            is ScreenAction.OnSubmitClicked -> processSubmitClicked()
            is PasswordInput -> processPasswordInput(action)
            is PasswordSubmitInput -> processPasswordSubmitInput(action)
            is ScreenAction.OnAuthFieldChange -> processAuthFieldChange()
        }
    }

    private fun processUsernameInput(action: UsernameInput) {
        _screenState.update { currentValue ->
            currentValue.copy(
                username = action.value
            )
        }
    }

    private fun processPasswordInput(action: PasswordInput) {
        _screenState.update { currentValue ->
            currentValue.copy(
                password = action.value
            )
        }
    }

    private fun processPasswordSubmitInput(action: PasswordSubmitInput) {
        _screenState.update { currentValue ->
            currentValue.copy(
                passwordSubmit = action.value,
            )
        }
    }

    private fun processAuthFieldChange() {
        _screenState.update { currentValue ->
            currentValue.copy(
                authFieldsState = currentValue.authFieldsState.inverse
            )
        }
    }

    private fun processSubmitClicked() {
        when (screenState.value.authFieldsState) {
            AuthFieldsState.AUTH -> auth()
            AuthFieldsState.REGISTER -> register()
        }
    }

    private fun register() {
        val state = screenState.value
        setLoadingState(ScreenLoadingState.Loading)

        interactor
            .register(
                username = state.username,
                password = state.password
            )
            .catch { throwable ->
                _screenEvents.emit(ScreenEvent.Error(throwable))
                setLoadingState(ScreenLoadingState.Content)
                handleError(throwable)
            }
            .onEach {
                _screenState.update { currentState ->
                    currentState.copy(
                        screenLoadingState = ScreenLoadingState.Content,
                        authFieldsState = AuthFieldsState.AUTH
                    )
                }
                _screenEvents.emit(ScreenEvent.SuccessRegistered)
            }
            .launchIn(viewModelScope)
    }

    private fun auth() {
        val state = screenState.value
        setLoadingState(ScreenLoadingState.Loading)

        interactor
            .auth(
                username = state.username,
                password = state.password
            )
            .catch { throwable ->
                _screenEvents.emit(ScreenEvent.Error(throwable))
                setLoadingState(ScreenLoadingState.Content)
                handleError(throwable)
            }
            .onEach {
                setLoadingState(ScreenLoadingState.Content)
                navigator.navigate(com.stslex.aproselection.core.navigation.destination.NavigationScreen.Home)
            }
            .launchIn(viewModelScope)
    }


    private fun setLoadingState(screenLoadingState: ScreenLoadingState) {
        _screenState.update { state ->
            state.copy(
                screenLoadingState = screenLoadingState
            )
        }
    }
}
