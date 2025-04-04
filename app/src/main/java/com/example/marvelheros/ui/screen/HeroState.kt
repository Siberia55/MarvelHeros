package com.example.marvelheros.ui.screen

import com.example.marvelheros.data.model.Hero

data class HeroUiState(
    val heroes: List<Hero> = emptyList(),
    val selectedHero: Hero? = null
)
