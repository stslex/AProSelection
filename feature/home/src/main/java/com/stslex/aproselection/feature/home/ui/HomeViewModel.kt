package com.stslex.aproselection.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.datastore.AppDataStore
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appDataStore: AppDataStore,
) : ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            appDataStore.setToken("")
            appDataStore.setUuid("")
        }
    }
}