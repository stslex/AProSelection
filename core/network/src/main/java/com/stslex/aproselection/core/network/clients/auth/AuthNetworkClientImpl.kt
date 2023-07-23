package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class AuthNetworkClientImpl(
    private val networkClient: NetworkClient
) : AuthNetworkClient {

    override suspend fun getHello(): HelloRequestModel = networkClient
        .apiClient
        .get {
            url.appendPathSegments("hello")
        }
        .body()
}