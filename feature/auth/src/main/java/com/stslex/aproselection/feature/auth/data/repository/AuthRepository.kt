package com.stslex.aproselection.feature.auth.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getHello(username: String): Flow<String>
}