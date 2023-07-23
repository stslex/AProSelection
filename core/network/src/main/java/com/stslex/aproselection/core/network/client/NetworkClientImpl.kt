package com.stslex.aproselection.core.network.client

import com.stslex.aproselection.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class NetworkClientImpl : NetworkClient {

    @OptIn(ExperimentalSerializationApi::class)
    override val client: HttpClient
        get() = HttpClient(Android) {

            expectSuccess = true

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
        }

    override val apiClient: HttpClient
        get() = client.config {
            defaultRequest {
                url {
                    host = "${BuildConfig.API_SERVER_HOST}$HOST_API_URL"
                    protocol = URLProtocol.HTTP
                }
//                headers {
//                    append(
//                        HEADER_AUTH,
//                        "$HEADER_AUTH_FIELD ${BuildConfig.API_KEY}"
//                    )
//                }
            }
        }

    companion object {
        private const val HOST_API_URL = "/api/v1"
        private const val HEADER_AUTH = "Authorization"
        private const val HEADER_AUTH_FIELD = "Client-ID"
    }
}