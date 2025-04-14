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
@JsonClass(generateAdapter = true)
data class MarvelHeroDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelThumbnail
) {
    fun toHero(): Hero = Hero(
        id = id,
        name = name,
        imageUrl = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}",// /*"https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",*/"${thumbnail.path}.${thumbnail.extension}",
        description = description

    )

}
@JsonClass(generateAdapter = true)
    data class MarvelThumbnail(
        val path: String,
        val extension: String
    )
