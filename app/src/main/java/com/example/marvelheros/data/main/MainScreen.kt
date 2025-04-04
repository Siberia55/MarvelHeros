package com.example.marvelheros.data.main

import androidx.compose.runtime.Composable
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.ui.screen.HeroUiState

@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit
) {
    MainContent(
        heroes = state.heroes,
        onHeroClick = onHeroClick
    )
}

//annotation class HeroUiState(val heroes: List<Hero>)
