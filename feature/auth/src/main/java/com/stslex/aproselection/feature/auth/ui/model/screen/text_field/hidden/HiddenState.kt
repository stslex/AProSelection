package com.stslex.aproselection.feature.auth.ui.model.screen.text_field.hidden

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
class HiddenState {

    private val _isHidden = mutableStateOf(true)
    var isHidden: Boolean
        get() = _isHidden.value
        set(value) {
            _isHidden.value = value
        }
}