package com.stslex.aproselection.feature.home.ui.model

import androidx.compose.runtime.Stable

@Stable
data class UserUi(
    val uuid: String,
    val username: String,
    val nickname: String
)
