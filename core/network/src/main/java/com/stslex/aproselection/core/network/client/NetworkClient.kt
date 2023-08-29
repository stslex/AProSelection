package com.stslex.aproselection.core.network.client

import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.SharedFlow

interface NetworkClient {

    val apiClient: HttpClient

    suspend fun auth(): UserAuthResponseModel
}