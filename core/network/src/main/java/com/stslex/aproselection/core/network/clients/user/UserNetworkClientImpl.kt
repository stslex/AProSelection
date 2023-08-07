package com.stslex.aproselection.core.network.clients.user

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.clients.user.model.UpdateUserInfoBody
import com.stslex.aproselection.core.network.clients.user.model.UserResponse
import com.stslex.aproselection.core.network.model.PagingRequest
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserNetworkClientImpl(
    private val networkClient: NetworkClient
) : UserNetworkClient {

    override suspend fun getUserList(
        page: Int,
        pageSize: Int
    ): List<UserResponse> = withContext(Dispatchers.IO) {
        networkClient.apiClient.get {
            url.appendPathSegments("user", "list")
            setBody(
                PagingRequest(
                    pageSize = pageSize,
                    pageNumber = page
                )
            )
        }.body()
    }

    override suspend fun getUser(
        uuid: String
    ): UserResponse = withContext(Dispatchers.IO) {
        networkClient.apiClient.get {
            url.appendPathSegments("user", uuid)
        }.body()
    }

    override suspend fun updateUserInfo(
        info: UpdateUserInfoBody
    ): UserResponse = withContext(Dispatchers.IO) {
        networkClient.apiClient.post {
            url.appendPathSegments("user", "update")
            setBody(info)
        }.body()
    }
}