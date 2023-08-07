package com.stslex.aproselection.core.network.client

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.BuildConfig
import com.stslex.aproselection.core.network.clients.auth.model.TokenResponse
import com.stslex.aproselection.core.network.model.ApiError
import com.stslex.aproselection.core.network.model.ApiErrorRespond
import com.stslex.aproselection.core.network.model.ApiErrorType
import com.stslex.aproselection.core.network.model.ApiErrorTypeRow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class NetworkClientImpl(
    private val dataStore: AppDataStore
) : NetworkClient {

    private val scope = CoroutineScope(SupervisorJob())
    private val _token = MutableStateFlow("")
    private val token = _token.asStateFlow()
    private val _uuid = MutableStateFlow("")
    private val uuid = _uuid.asStateFlow()

    // TODO (вынести из init блока)
    init {
        dataStore.token
            .onEach {
                _token.emit(it)
            }
            .launchIn(scope)

        dataStore.uuid
            .onEach {
                _uuid.emit(it)
            }
            .launchIn(scope)
    }

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

            HttpResponseValidator {
                handleResponseExceptionWithRequest(errorParser)
            }
        }

    override val apiClient: HttpClient
        get() = client.config {
            defaultRequest {
                url {
                    host = BuildConfig.API_SERVER_HOST
                    encodedPath = BuildConfig.API_VERSION
                    protocol = URLProtocol.HTTP
                    headers.append("API_KEY", BuildConfig.API_KEY)
                    headers.append("DEVICE_ID", "test")
                    headers.append("uuid", uuid.value)
                    contentType(ContentType.Application.Json)
                }
                bearerAuth(token = token.value)
            }
        }

    private val errorParser: suspend (Throwable, HttpRequest) -> Unit
        get() = { exception, _ ->
            val clientException = exception as? ResponseException
            val respond = clientException?.response?.body<ApiErrorRespond>()
            val apiErrorType = ApiErrorTypeRow
                .getByMessageCode(respond?.messageCode)
                .apiErrorType

            val apiError = ApiError(
                message = respond?.message.orEmpty(),
                type = apiErrorType
            )

            when (apiError.type) {
                is ApiErrorType.Unauthorized.Token -> regenerateToken()
                else -> throw apiError
            }
        }

    override suspend fun regenerateToken() {
        val token = apiClient
            .get {
                url.appendPathSegments("token")
            }
            .body<TokenResponse>()
            .token
        dataStore.setToken(token)
    }
}