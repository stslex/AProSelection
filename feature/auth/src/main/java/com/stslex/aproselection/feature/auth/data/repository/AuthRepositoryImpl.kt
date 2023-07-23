package com.stslex.aproselection.feature.auth.data.repository

import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val networkClient: AuthNetworkClient
) : AuthRepository {

    override fun getHello(username: String): Flow<String> = flow {
        val helloResponse = networkClient
            .getHello(username = username)
            .text
        emit(helloResponse)
    }
}