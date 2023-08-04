package com.stslex.aproselection.feature.home.domain.model

import com.stslex.aproselection.core.user.data.model.UserData

fun UserData.toDomain() = UserDomain(
    uuid = uuid,
    username = username,
    nickname = nickname
)