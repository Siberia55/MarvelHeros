package com.example.marvelheros.ui.screen

import com.example.marvelheros.domain.model.Hero

data class HeroUiState(
    val heroes: List<Hero> = emptyList(),
    val selectedHero: Hero? = null,
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val serverErrorMessage: String? = null,
    val onDismissHero: (() -> Unit)? = null,
    val heroDetailsLoading: Boolean = false,
    val heroDetailsProgress: Float = 0f,
    val heroDetailsError: Int? = null,
   )
