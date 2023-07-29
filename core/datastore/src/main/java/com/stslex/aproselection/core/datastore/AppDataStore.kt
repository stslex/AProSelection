package com.stslex.aproselection.core.datastore

import kotlinx.coroutines.flow.Flow

interface AppDataStore {

    val uuid: Flow<String>
    val token: Flow<String>

    suspend fun setUuid(uuid: String)

    suspend fun setToken(token: String)
}