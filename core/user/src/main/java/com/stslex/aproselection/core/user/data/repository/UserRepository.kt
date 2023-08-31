package com.stslex.aproselection.core.user.data.repository

import com.stslex.aproselection.core.user.data.model.UserData
import com.stslex.aproselection.core.user.data.model.UserUpdateData

interface UserRepository {

    suspend fun getUserList(page: Int, pageSize: Int): List<UserData>

    suspend fun getUser(uuid: String): UserData

    suspend fun updateUserInfo(info: UserUpdateData): UserData
}