package com.stslex.aproselection.core.network.clients.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthResponseModel(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("token")
    val token: String
)
