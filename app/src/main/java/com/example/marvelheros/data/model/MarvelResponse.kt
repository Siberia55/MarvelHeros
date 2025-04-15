package com.example.marvelheros.data.model

import com.squareup.moshi.JsonClass

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
