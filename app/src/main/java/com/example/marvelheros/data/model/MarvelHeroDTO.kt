package com.example.marvelheros.data.model

import com.example.marvelheros.domain.model.Hero
import com.squareup.moshi.JsonClass

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
        imageUrl = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}",
        description = description

    )

}
