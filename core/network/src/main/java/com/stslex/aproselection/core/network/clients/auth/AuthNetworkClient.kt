package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthSendModel
import kotlinx.coroutines.flow.Flow

interface AuthNetworkClient {

    suspend fun getHello(username: String): HelloRequestModel

    fun auth(user: UserAuthSendModel): Flow<UserAuthResponseModel>

    fun register(user: UserAuthSendModel): Flow<UserAuthResponseModel>
}