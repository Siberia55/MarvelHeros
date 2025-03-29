
package com.example.marvelheros.data.model

import androidx.annotation.Keep

@Keep // Эта аннотация поможет избежать проблем с Proguard
// старый код лаб1
/*
data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
) {
    // Дополнительные параметры по желанию:
   // constructor() : this(0, "", "", "") // Пустой конструктор для Firebase и т.д.
}

 */
data class Hero(
val id: Int,
val name: String,
val description: String,
val imageUrl: String,
val comicsCount: Int,
val seriesCount: Int,
val eventsCount: Int
) {
    companion object {
        fun fromDto(dto: CharacterDto) = Hero(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            imageUrl = dto.thumbnail.getImageUrl(),
            comicsCount = dto.comics.available,
            seriesCount = dto.series.available,
            eventsCount = dto.events.available
        )
    }
}