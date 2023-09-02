package com.stslex.aproselection.core.datastore.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.stslex.aproselection.core.core.Logger
import com.stslex.aproselection.core.datastore.UserCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Singleton
class AppDataStoreImpl @Inject constructor(
    private val context: Context
) : AppDataStore {

    companion object {
        private const val DATA_STORE_KEY = "app_data_store"
        private val TOKEN_KEY = stringPreferencesKey("TOKEN_KEY")
        private val USERNAME_KEY = stringPreferencesKey("USERNAME_KEY")
        private val PASSWORD_KEY = stringPreferencesKey("PASSWORD_KEY")
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_KEY)
    }

    private val _token = MutableStateFlow("")
    override val token: StateFlow<String>
        get() = _token.asStateFlow()

    private val _credential = MutableStateFlow(UserCredential())
    override val credential: StateFlow<UserCredential>
        get() = _credential.asStateFlow()

    override suspend fun setToken(token: String) {
        _token.emit(token)
        context.dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                set(TOKEN_KEY, token)
            }
        }
    }

    override suspend fun setCredential(credential: UserCredential) {
        _credential.emit(credential)
        context.dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                set(USERNAME_KEY, credential.username)
                set(PASSWORD_KEY, credential.password)
            }
        }
    }

    override suspend fun init() {
        context.dataStore.data
            .catch { error ->
                Logger.exception(error)
            }
            .onEach { prefs ->
                _token.value = prefs[TOKEN_KEY].orEmpty()
            }
            .flowOn(Dispatchers.IO)
            .launchIn(CoroutineScope(coroutineContext))
    }

    override suspend fun clear() {
        setToken("")
    }
}