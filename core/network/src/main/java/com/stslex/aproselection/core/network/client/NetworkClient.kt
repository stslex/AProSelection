package com.stslex.aproselection.core.network.client

import io.ktor.client.HttpClient

interface NetworkClient {

    val apiClient: HttpClient

    suspend fun regenerateToken(): String
}