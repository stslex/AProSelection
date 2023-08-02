package com.stslex.aproselection.ui

import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.controller.AuthController
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.core.ui.navigation.destination.NavigationScreen
import com.stslex.aproselection.core.ui.navigation.navigator.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class InitialAppViewModel(
    private val navigator: Navigator,
    private val controller: AuthController
) : BaseViewModel() {

    private val _isInitialAuth = MutableStateFlow<Boolean?>(null)
    val isInitialAuth: StateFlow<Boolean?>
        get() = _isInitialAuth.asStateFlow()

    fun init() {
        controller.isAuth
            .onEach { isAuth ->
                if (isInitialAuth.value == null) {
                    _isInitialAuth.emit(isAuth)
                }
                if (isAuth == false) {
                    navigator.navigate(NavigationScreen.Auth)
                }
            }
            .launchIn(viewModelScope)
    }
}