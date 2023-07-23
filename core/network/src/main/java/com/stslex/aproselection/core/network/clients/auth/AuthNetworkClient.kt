package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel

interface AuthNetworkClient {

    suspend fun getHello(username: String): HelloRequestModel
}