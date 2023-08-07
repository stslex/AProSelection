package com.stslex.aproselection.controller

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.client.NetworkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.coroutineContext

class AuthControllerImpl(
    private val dataStore: AppDataStore,
    private val networkClient: NetworkClient,
) : AuthController {

    private val _isAuth = MutableStateFlow<Boolean?>(null)
    override val isAuth: StateFlow<Boolean?>
        get() = _isAuth.asStateFlow()

    override suspend fun init() {
        dataStore.token
            .onEach { token ->
                if (token.isBlank()) {
                    networkClient.regenerateToken()
                }
            }
            .launchIn(CoroutineScope(coroutineContext))

        dataStore.uuid
            .onEach { uuid ->
                _isAuth.emit(uuid.isNotBlank())
            }
            .launchIn(CoroutineScope(coroutineContext))
    }
}