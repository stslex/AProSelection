package com.stslex.aproselection.core.user.data.model

import com.stslex.aproselection.core.network.clients.user.model.UpdateUserInfoBody
import com.stslex.aproselection.core.network.clients.user.model.UserResponse

fun List<UserResponse>.toData() = map { user ->
    user.toData()
}

fun UserResponse.toData() = UserData(
    uuid = uuid,
    username = username,
    nickname = nickname
)

fun UserUpdateData.toBody() = UpdateUserInfoBody(nickname)