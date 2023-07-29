package com.stslex.aproselection.core.network.clients.auth

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.HelloRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthSendModel
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


    override fun auth(
        user: UserAuthSendModel
    ): Flow<UserAuthResponseModel> = flow {
        val result = networkClient
            .apiClient
            .post {
                url.appendPathSegments("passport/auth")
                setBody<UserAuthSendModel>(user)
            }
            .body<UserAuthResponseModel>()
        emit(result)
    }
        .flowOn(Dispatchers.IO)

    override fun register(
        user: UserAuthSendModel
    ): Flow<UserAuthResponseModel> = flow {
        val result = networkClient
            .apiClient
            .post {
                url.appendPathSegments("passport/register")
                setBody<UserAuthSendModel>(user)
            }
            .body<UserAuthResponseModel>()
        emit(result)
    }
        .flowOn(Dispatchers.IO)
}