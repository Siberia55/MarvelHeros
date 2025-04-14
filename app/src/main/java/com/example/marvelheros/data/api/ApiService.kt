/*package com.example.marvelheros.data.api

import com.example.marvelheros.domain.model.Hero
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

 */
/*
package com.example.marvelheros.data.api

import com.example.marvelheros.data.model.ApiResponse
import com.example.marvelheros.data.model.CharacterDataContainer

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): ApiResponse<CharacterDataContainer>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int
    ): ApiResponse<CharacterDataContainer>

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
    }
}

 */

package com.example.marvelheros.data.api

import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.data.model.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }

    @GET("characters")
    suspend fun getHeroes(
        @Query("ts") timestamp: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): MarvelResponse
}