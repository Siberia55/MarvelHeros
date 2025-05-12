package com.example.marvelheros.ui.screen.heroState

import com.example.marvelheros.domain.model.Hero

data class HeroListState(
    val heroes : List<Hero> = emptyList(),
    val isLoading : Boolean = false,
    val error: String? = null
)
data class HeroDetailState (
    val hero: Hero? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)