package com.stslex.aproselection.feature.auth.data.repository

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.datastore.UserCredential
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthRequestModel
import com.stslex.aproselection.feature.auth.data.model.AuthMapper.toData
import com.stslex.aproselection.feature.auth.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class AuthRepositoryImpl(
    private val networkClient: AuthNetworkClient,
    private val dataSource: AppDataStore
) : AuthRepository {

    override fun auth(
        username: String,
        password: String
    ): Flow<UserModel> = networkClient.auth(
        UserAuthRequestModel(username, password)
    )
        .onEach { response ->
            dataSource.setToken(response.token)
            dataSource.setCredential(
                UserCredential(
                    username = username,
                    password = password
                )
            )
        }
        .map { user ->
            user.toData()
        }

    override suspend fun register(
        username: String,
        password: String
    ) {
        networkClient.register(UserAuthRequestModel(username, password))
    }
}