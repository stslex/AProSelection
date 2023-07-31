package com.stslex.aproselection.feature.auth.ui.model

import androidx.annotation.StringRes
import com.stslex.aproselection.feature.auth.R

enum class AuthState(
    @StringRes val buttonResId: Int
) {
    AUTH(R.string.auth),
    REGISTER(R.string.register);

    val inverse: AuthState
        get() = when (this) {
            AUTH -> REGISTER
            REGISTER -> AUTH
        }
}