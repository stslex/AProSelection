package com.stslex.aproselection.controller

import com.stslex.aproselection.core.datastore.AppDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.coroutineContext

class AuthControllerImpl(
    private val dataStore: AppDataStore,
) : AuthController {

    private val _isAuth = MutableStateFlow<Boolean?>(null)
    override val isAuth: StateFlow<Boolean?>
        get() = _isAuth.asStateFlow()

    override suspend fun init() {
        dataStore.token
            .onEach { token ->
                _isAuth.emit(token.isNotBlank())
            }
            .launchIn(CoroutineScope(coroutineContext))
    }
}