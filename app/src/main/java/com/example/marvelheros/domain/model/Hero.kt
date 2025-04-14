package com.example.marvelheros.domain.model

import androidx.annotation.Keep

@Keep // Эта аннотация поможет избежать проблем с Proguard

data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
)