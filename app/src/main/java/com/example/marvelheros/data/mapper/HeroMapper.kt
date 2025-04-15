package com.example.marvelheros.data.mapper

import com.example.marvelheros.data.local.entity.HeroEntity
import com.example.marvelheros.data.model.MarvelHeroDTO
import com.example.marvelheros.domain.model.Hero

object HeroMapper {
    // DTO → Entity
    fun dtoToEntity(dto: MarvelHeroDTO): HeroEntity = HeroEntity(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        thumbnailPath = dto.thumbnail.path.replace("http", "https"),
        thumbnailExtension = dto.thumbnail.extension,
        modified = "",
        imageUrl = "${dto.thumbnail.path.replace("http", "https")}.${dto.thumbnail.extension}"
    )

    // Entity → Domain
    fun entityToDomain(entity: HeroEntity): Hero = Hero(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        imageUrl = entity.imageUrl
    )

    // DTO → Domain
    fun dtoToDomain(dto: MarvelHeroDTO): Hero = Hero(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        imageUrl = "${dto.thumbnail.path.replace("http", "https")}.${dto.thumbnail.extension}"
    )

    fun MarvelHeroDTO.toEntity(): HeroEntity {
        return HeroEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            thumbnailPath = this.thumbnail.path,
            thumbnailExtension = this.thumbnail.extension,
            modified = "", // если нет поля в DTO — пока ставим пустую строку
            imageUrl = "${this.thumbnail.path.replace("http", "https")}.${this.thumbnail.extension}"
        )
    }
    fun HeroEntity.toDomain(): Hero {
        return Hero(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl,
            description = this.description
        )
    }
}