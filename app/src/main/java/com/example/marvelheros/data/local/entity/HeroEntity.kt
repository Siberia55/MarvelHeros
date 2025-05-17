package com.example.marvelheros.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")

data class HeroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val modified: String,
    val isFavorite: Boolean = false,
    val imageUrl: String
)