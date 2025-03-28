package com.example.marvelheros.data.api

import com.example.marvelheros.data.model.Hero
import retrofit2.http.GET

interface ApiService {
    // Базовый URL API
    companion object {
        const val BASE_URL = "https://ваш.сервер.api/"
    }

    // Пример запроса для получения списка героев
    @GET("heroes")
    suspend fun getAllHeroes(): List<Hero>
}