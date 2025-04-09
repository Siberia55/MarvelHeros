package com.example.marvelheros.ui.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.ui.components.FullScreenHeroDetails
//import com.example.marvelheros.ui.components.MainContent

//import com.example.marvelheros.ui.screen.HeroUiState
/*
@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit,
    onDismissHero: () -> Unit
) {
    if (state.selectedHero != null) {
        FullScreenHeroDetails(
            hero = state.selectedHero,
            onDismiss = onDismissHero
        )
    } else {
        MainContent(
            heroes = state.heroes,
            onHeroClick = onHeroClick
        )
    }
}

 */
@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit,
    onDismissHero: () -> Unit
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.errorMessage != null) {
        Text("Ошибка: ${state.errorMessage}")
    } else {
        if (state.selectedHero != null) {
            FullScreenHeroDetails(hero = state.selectedHero, onDismiss = onDismissHero)
        } else {
            MainContent(heroes = state.heroes, onHeroClick = onHeroClick)
        }
    }
}