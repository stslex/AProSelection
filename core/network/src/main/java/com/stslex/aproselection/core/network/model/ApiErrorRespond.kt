package com.stslex.aproselection.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorRespond(
    @SerialName("message_code")
    val messageCode: Int,
    @SerialName("message")
    override val message: String
) : Throwable(
    message = message
)