package com.stslex.aproselection.core.network.model

enum class ApiErrorTypeRow(
    val messageCode: Int
) {
    UNAUTHORIZED_TOKEN(1),
    UNAUTHORIZED_API_KEY(2),
    UNAUTHORIZED_DEVICE_ID(3),
    AUTHENTICATION_SAVE_USER(100),
    AUTHENTICATION_INVALID_PASSWORD(101),
    AUTHENTICATION_USER_IS_EXIST(102),
    AUTHENTICATION_USER_IS_NOT_EXIST(103),
    UNDEFINED(-1);

    val apiErrorType: ApiErrorType
        get() = when (this) {
            UNAUTHORIZED_TOKEN -> ApiErrorType.Unauthorized.Token
            UNAUTHORIZED_API_KEY -> ApiErrorType.Unauthorized.ApiKey
            UNAUTHORIZED_DEVICE_ID -> ApiErrorType.Unauthorized.DeviceId
            AUTHENTICATION_SAVE_USER -> ApiErrorType.Authentication.SaveUser
            AUTHENTICATION_INVALID_PASSWORD -> ApiErrorType.Authentication.InvalidPassword
            AUTHENTICATION_USER_IS_EXIST -> ApiErrorType.Authentication.UserIsExist
            AUTHENTICATION_USER_IS_NOT_EXIST -> ApiErrorType.Authentication.UserIsNotExist
            UNDEFINED -> ApiErrorType.Unhandled
        }

    companion object {

        fun getByMessageCode(
            code: Int?
        ) = entries
            .firstOrNull { type ->
                type.messageCode == code
            }
            ?: UNDEFINED
    }
}