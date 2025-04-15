package com.example.marvelheros.data.mapper

import com.example.marvelheros.data.local.entity.HeroEntity
import com.example.marvelheros.data.model.MarvelHeroDTO
import com.example.marvelheros.domain.model.Hero

object HeroMapper {
    fun MarvelHeroDTO.toEntity(): HeroEntity {
        return HeroEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            thumbnailPath = this.thumbnail.path.replace("http", "https"),
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
    fun  MarvelHeroDTO.toDomain(): Hero {
        return Hero(
            id = this.id,
            name = this.name,
            description = this.description,
            imageUrl = "${this.thumbnail.path.replace("http", "https")}.${this.thumbnail.extension}"

        )
    }
}