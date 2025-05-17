package com.example.marvelheros.data.api

import com.example.marvelheros.data.model.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"


    }

    @GET("characters")
    suspend fun getHeroes(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
    ): MarvelResponse

    @GET("characters/{characterId}")
    suspend fun getHeroById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): MarvelResponse
}