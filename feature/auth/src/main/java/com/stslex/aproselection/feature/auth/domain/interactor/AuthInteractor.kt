package com.stslex.aproselection.feature.auth.domain.interactor

import com.stslex.aproselection.feature.auth.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthInteractor {

    fun auth(username: String, password: String): Flow<UserModel>

    fun register(username: String, password: String): Flow<UserModel>
}