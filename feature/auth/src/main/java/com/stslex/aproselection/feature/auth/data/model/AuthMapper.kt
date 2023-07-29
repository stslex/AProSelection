package com.stslex.aproselection.feature.auth.data.model

import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel

object AuthMapper {

    fun UserAuthResponseModel.toData(): UserModel = UserModel(
        uuid = uuid,
        username = username,
        nickname = nickname
    )
}