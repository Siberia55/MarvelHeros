package com.example.marvelheros.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelHeroDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelThumbnail
)
