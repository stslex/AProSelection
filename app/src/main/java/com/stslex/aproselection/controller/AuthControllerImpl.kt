package com.stslex.aproselection.controller

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.core.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AuthControllerImpl(
    private val dataStore: AppDataStore,
    private val networkClient: NetworkClient,
) : AuthController {

    private val scope = CoroutineScope(SupervisorJob())
    private val _isAuth = MutableStateFlow<Boolean?>(null)
    override val isAuth: StateFlow<Boolean?>
        get() = _isAuth.asStateFlow()

    override fun init() {
        dataStore.token
            .catch { error ->
                Logger.exception(error)
            }
            .onEach { token ->
                if (token.isBlank()) {
                    networkClient.regenerateToken()
                }
            }
            .launchIn(scope)

        dataStore.uuid
            .catch { error ->
                Logger.exception(error)
            }
            .onEach { uuid ->
                _isAuth.emit(uuid.isNotBlank())
            }
            .launchIn(scope)
    }
}