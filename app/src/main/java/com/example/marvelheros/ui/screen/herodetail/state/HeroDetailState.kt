package com.example.marvelheros.ui.screen.herodetail.state

import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.utils.ErrorCode

data class HeroDetailState(
    val hero: Hero? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val errorCode: ErrorCode? = null
)