package com.example.marvelheros.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//data/model/MarvelResponse.kt
@JsonClass(generateAdapter = true)
data class MarvelResponse(
    val data: MarvelData,
    val code: Int,
    val status: String
)
@JsonClass(generateAdapter = true)
data class MarvelData(
    val results: List<MarvelHeroDTO>
)
