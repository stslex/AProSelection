package com.stslex.aproselection.core.datastore

import kotlinx.coroutines.flow.StateFlow

interface AppDataStore {

    val uuid: StateFlow<String>
    val token: StateFlow<String>

    suspend fun setUuid(uuid: String)

    suspend fun setToken(token: String)

    suspend fun clear()

    suspend fun init()
}