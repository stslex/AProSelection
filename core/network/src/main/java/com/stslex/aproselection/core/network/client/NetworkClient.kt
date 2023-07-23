package com.stslex.aproselection.core.network.client

import io.ktor.client.HttpClient

interface NetworkClient {

    val client: HttpClient

    val apiClient: HttpClient
}