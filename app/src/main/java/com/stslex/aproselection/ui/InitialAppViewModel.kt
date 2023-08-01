package com.stslex.aproselection.ui

import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class InitialAppViewModel(
    dataStore: AppDataStore,
    navigate: (NavigationScreen) -> Unit,
    networkClient: NetworkClient,
) : BaseViewModel() {

    private val _isAuth = MutableStateFlow<Boolean?>(null)
    val isAuth: StateFlow<Boolean?>
        get() = _isAuth.asStateFlow()

    init {
        dataStore.token
            .catch { error ->
                handleError(error)
            }
            .onEach { token ->
                if (token.isBlank()) {
                    networkClient.regenerateToken()
                }
            }
            .launchIn(viewModelScope)

        dataStore.uuid
            .catch { error ->
                handleError(error)
            }
            .onEach { uuid ->
                _isAuth.emit(uuid.isNotBlank())
                if (uuid.isBlank()) {
                    navigate(NavigationScreen.Auth)
                }
            }
            .launchIn(viewModelScope)
    }
}