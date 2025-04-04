package com.example.marvelheros.data.main

import androidx.compose.runtime.Composable
import com.example.marvelheros.data.model.Hero

@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit
) {
    MainContent(heroes = state.heroes, onHeroClick = onHeroClick)
}