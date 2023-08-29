package com.stslex.aproselection.feature.auth.domain.interactor

import com.stslex.aproselection.feature.auth.data.model.UserModel
import com.stslex.aproselection.feature.auth.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {

    override fun auth(
        username: String,
        password: String
    ): Flow<UserModel> = repository.auth(
        username = username,
        password = password
    )

    override suspend fun register(
        username: String,
        password: String
    ) {
        repository.register(
            username = username,
            password = password
        )
    }
}