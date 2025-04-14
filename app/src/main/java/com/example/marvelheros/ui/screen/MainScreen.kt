package com.example.marvelheros.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.components.FullScreenHeroDetails

import com.example.marvelheros.ui.screen.HeroUiState

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


