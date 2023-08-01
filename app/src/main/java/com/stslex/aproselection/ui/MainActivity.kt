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

    private val dataStore by inject<AppDataStore>()
    private val networkClient by inject<NetworkClient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                InitialApp()
            }
        }
    }
}
