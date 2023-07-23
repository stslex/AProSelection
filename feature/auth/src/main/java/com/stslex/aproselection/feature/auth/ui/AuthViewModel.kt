package com.stslex.aproselection.feature.auth.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.aproselection.core.network.client.NetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AuthViewModel(
    private val networkClient: NetworkClient
) : ViewModel() {

    val text: StateFlow<String>
        get() = flow {
            val result = networkClient.apiClient.get {
                url.appendPathSegments("hello")
            }
                .body<HelloRequest>()
                .hello
            emit(result)
        }
            .catch {
                Log.e(javaClass.simpleName, it.message, it)
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, "")
}

@Serializable
data class HelloRequest(
    @SerialName("text")
    val hello: String
)