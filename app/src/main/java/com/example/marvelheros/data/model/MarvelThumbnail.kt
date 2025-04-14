package com.example.marvelheros.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelThumbnail(
    val path: String,
    val extension: String
)