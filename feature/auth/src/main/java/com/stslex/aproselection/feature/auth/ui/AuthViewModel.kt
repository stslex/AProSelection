package com.stslex.aproselection.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class AuthViewModel : ViewModel() {

    val text: StateFlow<String>
        get() = flowOf("Hello")
            .stateIn(viewModelScope, SharingStarted.Lazily, "")
}