package com.example.marvelheros.data.api // Важно: проверьте правильность пакета!

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