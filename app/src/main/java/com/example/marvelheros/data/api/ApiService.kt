package com.example.marvelheros.data.api

import com.example.marvelheros.data.model.Hero
import retrofit2.http.GET

interface ApiService {
    // Базовый URL API
    companion object {
        const val BASE_URL = "https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0/"
    }

    // Пример запроса для получения списка героев
    @GET("heroes"/*"/characters/{characterId}"*/)
   // suspend fun getAllHeroes(): List<Hero>
    suspend fun getHeroes(): List<Hero>
}