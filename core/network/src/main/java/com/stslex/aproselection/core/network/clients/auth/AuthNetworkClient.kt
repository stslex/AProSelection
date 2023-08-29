package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthRequestModel
import kotlinx.coroutines.flow.Flow

interface AuthNetworkClient {

    fun auth(user: UserAuthRequestModel): Flow<UserAuthResponseModel>

    suspend fun register(user: UserAuthRequestModel)
}