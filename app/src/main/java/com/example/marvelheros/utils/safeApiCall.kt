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
        e.printStackTrace()
        Log.e(errorTag, "Нет подключения к сети: ${e.message}")
        val fallbackResult = fallback()
        if (fallbackResult != null) {
            MyResult.Success(fallbackResult)
        } else {
            MyResult.Error("Нет подключения к сети")
        }
    } catch (e: HttpException) {
        e.printStackTrace()
        Log.e(errorTag, "Ошибка сервера: ${e.message}")
        MyResult.Error("Ошибка сервера: ${e.message}")
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e(errorTag, "Неизвестная ошибка: ${e.message}")
        val fallbackResult = fallback()
        if (fallbackResult != null) {
            MyResult.Success(fallbackResult)
        } else {
            MyResult.Error("Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}")
        }
    }
}

