
package com.example.marvelheros.data.model

import androidx.annotation.Keep

@Keep // Эта аннотация поможет избежать проблем с Proguard

data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
) {
    // Дополнительные параметры по желанию:
   // constructor() : this(0, "", "", "") // Пустой конструктор для Firebase и т.д.
}