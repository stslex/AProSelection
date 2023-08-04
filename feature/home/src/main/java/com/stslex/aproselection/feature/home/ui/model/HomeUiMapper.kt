package com.stslex.aproselection.feature.home.ui.model

import com.stslex.aproselection.feature.home.domain.model.UserDomain

fun UserDomain.toPresentation() = UserUi(
    uuid = uuid,
    username = username,
    nickname = nickname
)