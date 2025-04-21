package com.example.marvelheros.utils

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T,
    fallback: suspend () -> T? = { null },
    errorTag: String = "safeApiCall"
): MyResult<T> {
    return try {
        val result = apiCall()
        MyResult.Success(result)
    } catch (e: IOException) {
        Log.e(errorTag, "Нет подключения: ${e.message}")
        fallback()?.let { MyResult.Success(it) }
            ?: MyResult.Error(ErrorCode.NETWORK_ERROR)
    } catch (e: HttpException) {
        Log.e(errorTag, "Ошибка сервера: ${e.message}")
        MyResult.Error(ErrorCode.SERVER_ERROR, e.message())
    } catch (e: Exception) {
        Log.e(errorTag, "Неизвестная ошибка: ${e.message}")
        fallback()?.let { MyResult.Success(it) }
            ?: MyResult.Error(ErrorCode.UNKNOWN_ERROR, e.localizedMessage)
    }
}

