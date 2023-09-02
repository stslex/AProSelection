package com.stslex.aproselection.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.core.navigation.destination.NavigationScreen
import com.stslex.aproselection.core.navigation.navigator.Navigator
import dagger.Lazy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class InitialAppViewModel @Inject constructor(
    private val controller: AuthController,
    private val navigator: Lazy<Navigator>,
) : ViewModel() {

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
                    navigator.get().navigate(NavigationScreen.Auth)
                }
            }
            .launchIn(viewModelScope)
    }
}