package com.stslex.aproselection.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.ui.navigation.NavigationScreen
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appDataStore: AppDataStore,
    private val navigation: (NavigationScreen) -> Unit,
) : ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            appDataStore.setToken("")
            appDataStore.setUuid("")
        }
    }
}