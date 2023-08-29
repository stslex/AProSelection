package com.stslex.aproselection.core.network.clients.auth.model

import com.stslex.aproselection.core.datastore.UserCredential

fun UserAuthRequestModel.toStorage() = UserCredential(
    username = username,
    password = password
)