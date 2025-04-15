package com.example.marvelheros.data.model

import com.example.marvelheros.data.local.entity.HeroEntity
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
    fun toEntity(): HeroEntity = HeroEntity(
        id = id,
        name = name,
        description = description,
        thumbnailPath = thumbnail.path,
        thumbnailExtension = thumbnail.extension,
        modified = "", // если нет такого поля — либо добавить в DTO, либо оставить пустым
        imageUrl = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
    )
    fun HeroEntity.toDomain(): Hero = Hero(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description
    )
}
