package com.stslex.aproselection.core.network.clients.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserInfoBody(
    @SerialName("nickname")
    val nickname: String
)
