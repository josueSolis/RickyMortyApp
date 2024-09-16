package com.omni.pro.characters.commons

sealed class ApiException(cause: Throwable?) : Exception(cause) {
    data class NetworkError(val error: Throwable? = null) : ApiException(error)

    data class UnknownError(val error: Throwable? = null) : ApiException(error)

    data class ApiError(val detail: String) : ApiException(null)
}