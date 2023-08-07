package com.stslex.aproselection.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.stslex.aproselection.core.core.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.coroutineContext

class AppDataStoreImpl(
    private val context: Context
) : AppDataStore {

    companion object {
        private const val DATA_STORE_KEY = "app_data_store"
        private val UUID_KEY = stringPreferencesKey("UUID_KEY")
        private val TOKEN_KEY = stringPreferencesKey("TOKEN_KEY")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_KEY)
    }

    private val _uuid = MutableStateFlow("")
    override val uuid: StateFlow<String>
        get() = _uuid.asStateFlow()

    private val _token = MutableStateFlow("")
    override val token: StateFlow<String>
        get() = _token.asStateFlow()

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

    override suspend fun init() {
        context.dataStore.data
            .catch { error ->
                Logger.exception(error)
            }
            .onEach { prefs ->
                _uuid.value = prefs[UUID_KEY].orEmpty()
                _token.value = prefs[TOKEN_KEY].orEmpty()
            }
            .flowOn(Dispatchers.IO)
            .launchIn(CoroutineScope(coroutineContext))
    }

    override suspend fun clear() {
        setUuid("")
        setToken("")
    }
}