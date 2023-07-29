package com.stslex.aproselection.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStoreImpl(
    private val context: Context
) : AppDataStore {

    companion object {
        private const val DATA_STORE_KEY = "app_data_store"
        private val UUID_KEY = stringPreferencesKey("UUID_KEY")
        private val TOKEN_KEY = stringPreferencesKey("TOKEN_KEY")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_KEY)
    }

    override val uuid: Flow<String>
        get() = context.dataStore.data.map { prefs ->
            prefs[UUID_KEY].orEmpty()
        }

    override val token: Flow<String>
        get() = context.dataStore.data.map { prefs ->
            prefs[TOKEN_KEY].orEmpty()
        }

    override suspend fun setUuid(uuid: String) {
        context.dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                set(UUID_KEY, uuid)
            }
        }
    }


    override suspend fun setToken(token: String) {
        context.dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                set(TOKEN_KEY, token)
            }
        }
    }
}