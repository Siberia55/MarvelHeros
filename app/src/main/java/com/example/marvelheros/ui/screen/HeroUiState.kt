package com.example.marvelheros.ui.screen

import com.example.marvelheros.domain.model.Hero

data class HeroUiState(
    val heroes: List<Hero> = emptyList(),
    val selectedHero: Hero? = null,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val onDismissHero: (() -> Unit)? = null,
   )
