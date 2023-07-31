package com.stslex.aproselection.feature.auth.ui

import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import com.stslex.aproselection.feature.auth.ui.model.ScreenEvent
import com.stslex.aproselection.feature.auth.ui.model.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AuthViewModel(
    private val interactor: AuthInteractor
) : BaseViewModel() {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Content)
    val screenState: StateFlow<ScreenState>
        get() = _screenState.asStateFlow()

    private val _screenEvents: MutableSharedFlow<ScreenEvent> = MutableSharedFlow()
    val screenEvent: SharedFlow<ScreenEvent>
        get() = _screenEvents.asSharedFlow()

    fun register(
        username: String,
        password: String
    ) {
        _screenState.value = ScreenState.Loading

        interactor
            .auth(
                username = username,
                password = password
            )
            .catch { throwable ->
                _screenEvents.emit(ScreenEvent.Error(throwable))
                _screenState.value = ScreenState.Content
                handleError(throwable)
            }
            .onEach {
                _screenState.value = ScreenState.Content
            }
            .launchIn(viewModelScope)
    }

    fun auth(username: String, password: String) {
        _screenState.value = ScreenState.Loading

        interactor
            .auth(
                username = username,
                password = password
            )
            .catch { throwable ->
                _screenEvents.emit(ScreenEvent.Error(throwable))
                _screenState.value = ScreenState.Content
                handleError(throwable)
            }
            .onEach {
                _screenState.value = ScreenState.Content
            }
            .launchIn(viewModelScope)
    }
}
