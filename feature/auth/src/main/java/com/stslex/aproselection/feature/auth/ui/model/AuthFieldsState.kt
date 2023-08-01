package com.stslex.aproselection.feature.auth.ui.model

import androidx.annotation.StringRes
import com.stslex.aproselection.feature.auth.R

enum class AuthFieldsState(
    @StringRes val buttonResId: Int
) {
    AUTH(R.string.auth),
    REGISTER(R.string.register);

    val inverse: AuthFieldsState
        get() = when (this) {
            AUTH -> REGISTER
            REGISTER -> AUTH
        }
}