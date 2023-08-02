package com.stslex.aproselection.feature.auth.ui.model.screen.text_field

abstract class AuthTextField {

    abstract val text: String
    abstract val label: Int
    abstract val sendAction: (text: String) -> Unit

    val supportingEndText: String
        get() = "${text.length}/$MAX_SYMBOL_COUNT"

    fun onTextChange(value: String) {
        if (text == value || value.length > MAX_SYMBOL_COUNT) return
        sendAction(value)
    }

    companion object {
        private const val MAX_SYMBOL_COUNT = 10
    }
}
