package com.stslex.aproselection.core.network.clients.user

import com.stslex.aproselection.core.network.clients.user.model.UpdateUserInfoBody
import com.stslex.aproselection.core.network.clients.user.model.UserResponse

interface UserNetworkClient {

    suspend fun getUserList(page: Int, pageSize: Int): List<UserResponse>

    suspend fun getUser(uuid: String): UserResponse

    suspend fun updateUserInfo(info: UpdateUserInfoBody): UserResponse
}