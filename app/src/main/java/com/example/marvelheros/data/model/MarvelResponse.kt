/*package com.example.marvelheros.data.model

// data/model/MarvelResponse.kt
data class MarvelResponse(
    val data: MarvelData
)

data class MarvelData(
    val results: List<MarvelHeroDTO>
)

data class MarvelHeroDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelThumbnail
) {
    fun toHero(): Hero = Hero(
        id = id,
        name = name,
        imageUrl = "${thumbnail.path}.${thumbnail.extension}",
        description = description
    )
}

    data class MarvelThumbnail(
        val path: String,
        val extension: String
    )
*/