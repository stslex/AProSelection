package com.stslex.aproselection.core.user.data.repository

import com.stslex.aproselection.core.network.clients.user.UserNetworkClient
import com.stslex.aproselection.core.user.data.model.UserData
import com.stslex.aproselection.core.user.data.model.UserUpdateData
import com.stslex.aproselection.core.user.data.model.toBody
import com.stslex.aproselection.core.user.data.model.toData
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkClient: UserNetworkClient
) : UserRepository {

    override suspend fun getUserList(
        page: Int,
        pageSize: Int
    ): List<UserData> = networkClient
        .getUserList(
            page = page,
            pageSize = pageSize
        ).toData()

    override suspend fun getUser(
        uuid: String
    ): UserData = networkClient.getUser(uuid).toData()

    override suspend fun updateUserInfo(
        info: UserUpdateData
    ): UserData = networkClient.updateUserInfo(info.toBody()).toData()
}