package com.example.marvelheros.ui.screen.herolist.state

import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.utils.ErrorCode

data class HeroListState(
    val heroes: List<Hero> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val errorCode: ErrorCode? = null
)