package com.stslex.aproselection.core.network.clients.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HelloRequestModel(
    @SerialName("text")
    val text: String
)
