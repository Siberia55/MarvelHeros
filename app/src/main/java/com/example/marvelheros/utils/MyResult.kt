package com.example.marvelheros.utils

sealed class MyResult<out T> {
    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(
        val errorCode: ErrorCode,
        val message: String? = null
    ) : MyResult<Nothing>()
}

enum class ErrorCode {
    NETWORK_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}