package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.toStorage
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class AuthNetworkClientImpl(
    private val networkClient: NetworkClient,
    private val dataStore: AppDataStore
) : AuthNetworkClient {

    override fun auth(
        user: UserAuthRequestModel
    ): Flow<UserAuthResponseModel> = flow {
        dataStore.setCredential(user.toStorage())
        emit(networkClient.auth())
    }.flowOn(Dispatchers.IO)

    override suspend fun register(
        user: UserAuthRequestModel
    ) {
        withContext(Dispatchers.IO) {
            networkClient
                .apiClient
                .post {
                    url.appendPathSegments("passport/registration")
                    setBody<UserAuthRequestModel>(user)
                }
        }
    }
}