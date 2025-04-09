package com.example.marvelheros.ui.screen

import com.example.marvelheros.data.model.Hero

data class HeroUiState(
    val heroes: List<Hero> = emptyList(),
    val selectedHero: Hero? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val onDismissHero: (() -> Unit)? = null,
    //val onHeroClick: (() -> Unit)
)
