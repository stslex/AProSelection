package com.stslex.aproselection.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingRequest(
    @SerialName("page_size")
    val pageSize: Int,
    @SerialName("page_number")
    val pageNumber: Int
)
