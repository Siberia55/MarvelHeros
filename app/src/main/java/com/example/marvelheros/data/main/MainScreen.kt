package com.example.marvelheros.data.main

import androidx.compose.runtime.Composable
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.ui.screen.HeroUiState


//-----gpt

@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit
) {
    android.util.Log.d("HeroDebug", "Heroes in MainScreen: ${state.heroes}")
    MainContent(
        heroes = state.heroes,
        onHeroClick = onHeroClick
    )
}

//------DS
