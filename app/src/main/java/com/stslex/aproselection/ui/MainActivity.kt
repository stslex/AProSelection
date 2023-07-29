package com.stslex.aproselection.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.ui.theme.AppTheme
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val dataStore by inject<AppDataStore>()
    val networkClient by inject<NetworkClient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore.token
            .catch {
                Log.e(it.message, javaClass.simpleName, it)
            }
            .onEach {
                if (it.isBlank()) {
                    networkClient.regenerateToken()
                }
            }
            .launchIn(lifecycleScope)
        setContent {
            AppTheme {
                InitialApp()
            }
        }
    }
}
