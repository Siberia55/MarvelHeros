package com.example.marvelheros.data.model

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val events: EventList
) {
    fun toHero() = Hero(
        id = id,
        name = name,
        imageUrl = "${thumbnail.path}.${thumbnail.extension}",
        description = description
    )
}