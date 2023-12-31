package com.stslex.aproselection.core.network.clients.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthRequestModel(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)
