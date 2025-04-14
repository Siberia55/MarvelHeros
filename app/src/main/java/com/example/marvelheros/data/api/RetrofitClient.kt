/*package com.example.marvelheros.data.api // Важно: проверьте правильность пакета!

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Создаем объект (одиночка) для работы с Retrofit
object RetrofitClient {

    // Ленивая инициализация клиента
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL) // Базовый URL из ApiService
            .addConverterFactory(MoshiConverterFactory.create()) // Конвертер JSON
            .build()
            .create(ApiService::class.java) // Создаем реализацию ApiService
    }
}

 */
// data/api/RetrofitClient.kt
/*
package com.example.marvelheros.data.api

import com.example.marvelheros.BuildConfig.MARVEL_PRIVATE_KEY
import com.example.marvelheros.BuildConfig.MARVEL_PUBLIC_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val TIMEOUT = 30L

    val instance: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val timestamp = System.currentTimeMillis().toString()
                val hash = (timestamp + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY).md5()

                val url = original.url.newBuilder()
                    .addQueryParameter("ts", timestamp)
                    .addQueryParameter("apikey", MARVEL_PUBLIC_KEY)
                    .addQueryParameter("hash", hash)
                    .build()

                chain.proceed(original.newBuilder().url(url).build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}

 */
/*
package com.example.marvelheros.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    val instance: ApiService = run {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

}*/