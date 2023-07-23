package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthNetworkClientImpl(
    private val networkClient: NetworkClient
) : AuthNetworkClient {

    override suspend fun getHello(
        username: String
    ): HelloRequestModel = withContext(Dispatchers.IO) {
        networkClient
            .apiClient
            .get {
                url.appendPathSegments("hello", username)
            }
            .body()
    }
}