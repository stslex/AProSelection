package com.stslex.aproselection.core.datastore.store

import com.stslex.aproselection.core.datastore.UserCredential
import kotlinx.coroutines.flow.StateFlow

interface AppDataStore {

    val token: StateFlow<String>

    val credential: StateFlow<UserCredential>

    suspend fun setToken(token: String)

    suspend fun setCredential(credential: UserCredential)

    suspend fun clear()

    suspend fun init()
}