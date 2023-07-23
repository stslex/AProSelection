package com.stslex.aproselection.feature.auth.ui

import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AuthViewModel(
    private val interactor: AuthInteractor
) : BaseViewModel() {

    private val _text: MutableStateFlow<String> = MutableStateFlow("...")

    val text: StateFlow<String>
        get() = _text.asStateFlow()

    fun setUsername(username: String) {
        _text.value = "..."
        interactor.getHello(username)
            .catch { throwable ->
                handleError(throwable)
            }
            .onEach { receivedText ->
                _text.emit(receivedText)
            }
            .launchIn(viewModelScope)
    }
}
