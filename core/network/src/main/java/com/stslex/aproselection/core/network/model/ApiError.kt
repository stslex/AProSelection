package com.stslex.aproselection.core.network.model

data class ApiError(
    override val message: String,
    val type: ApiErrorType
) : Throwable(
    message = message
)

sealed interface ApiErrorType {

    sealed interface Unauthorized : ApiErrorType {

        data object Token : Unauthorized
        data object ApiKey : Unauthorized
        data object DeviceId : Unauthorized
    }

    sealed interface Authentication : ApiErrorType {

        data object SaveUser : Authentication
        data object InvalidPassword : Authentication
        data object InvalidPasswordFormat : Authentication
        data object UserIsExist : Authentication
        data object UserIsNotExist : Authentication
    }

    data object Unhandled : ApiErrorType
}
