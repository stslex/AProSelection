package com.stslex.aproselection.core.network.client

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.network.BuildConfig
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthRequestModel
import com.stslex.aproselection.core.network.clients.auth.model.UserAuthResponseModel
import com.stslex.aproselection.core.network.model.ApiError
import com.stslex.aproselection.core.network.model.ApiErrorRespond
import com.stslex.aproselection.core.network.model.ApiErrorType
import com.stslex.aproselection.core.network.model.ApiErrorTypeRow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class NetworkClientImpl(
    private val dataStore: AppDataStore
) : NetworkClient {

    @OptIn(ExperimentalSerializationApi::class)
    private val client: HttpClient = HttpClient(Android) {
        expectSuccess = true

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val token = dataStore.token.value
                    BearerTokens(token, token)
                }
            }
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

    override val apiClient: HttpClient = client.config {
        defaultRequest {
            url("${BuildConfig.API_SERVER_HOST}") {
                host = BuildConfig.API_SERVER_HOST
                encodedPath = BuildConfig.API_VERSION
                protocol = URLProtocol.HTTP
                headers.append(API_KEY_HEADER, BuildConfig.API_KEY)
                contentType(ContentType.Application.Json)
            }
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
                is ApiErrorType.Unauthorized.Token -> auth()

                else -> throw apiError
            }
        }

    override suspend fun auth(): UserAuthResponseModel = withContext(Dispatchers.IO) {
        val credential = dataStore.credential.value
        val username = credential.username
        val password = credential.password
        if (username.isBlank() || password.isBlank()) {
            throw ApiError(
                message = "need auth",
                type = ApiErrorType.Authentication.InvalidPassword
            )
        }
        val user = UserAuthRequestModel(
            username = dataStore.credential.value.username,
            password = dataStore.credential.value.password
        )
        apiClient
            .post {
                url.path("passport/login")
                setBody<UserAuthRequestModel>(user)
            }
            .body<UserAuthResponseModel>()
            .also { authModel ->
                dataStore.setToken(token = authModel.token)
            }
    }

    companion object {
        private const val API_KEY_HEADER = "x-api-key"
    }
}