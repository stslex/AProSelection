package com.stslex.aproselection.feature.auth.ui.model

import androidx.annotation.StringRes
import com.stslex.aproselection.feature.auth.R

enum class AuthFieldsState(
    @StringRes val buttonResId: Int,
    @StringRes val titleResId: Int
) {
    AUTH(
        buttonResId = R.string.auth_button_choose_log_in,
        titleResId = R.string.auth_title_auth
    ),
    REGISTER(
        buttonResId = R.string.auth_button_choose_register,
        titleResId = R.string.auth_title_register
    );

    val inverse: AuthFieldsState
        get() = when (this) {
            AUTH -> REGISTER
            REGISTER -> AUTH
        }
}