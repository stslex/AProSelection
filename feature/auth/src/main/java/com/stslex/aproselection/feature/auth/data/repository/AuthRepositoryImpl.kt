package com.stslex.aproselection.feature.auth.data.repository

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthSendModel
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
        UserAuthSendModel(username, password)
    )
        .onEach { response ->
            dataSource.setToken(response.token)
            dataSource.setUuid(response.uuid)
        }
        .map { user ->
            user.toData()
        }

    override fun register(
        username: String,
        password: String
    ): Flow<UserModel> = networkClient.register(
        UserAuthSendModel(username, password)
    )
        .onEach { response ->
            dataSource.setToken(response.token)
            dataSource.setUuid(response.uuid)
        }
        .map { user ->
            user.toData()
        }
}