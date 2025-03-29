package com.example.marvelheros.ui.heroeslist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelheros.HeroItem
import com.example.marvelheros.data.model.Hero

class HeroesListScreen {
    @Composable
    fun HeroesListScreen(
        viewModel: HeroesListViewModel = hiltViewModel(),
        onHeroClick: (Hero) -> Unit
    ) {
        val state by viewModel.state.collectAsState()

        when (state) {
            is HeroesListState.Data -> {
                val dataState = state as HeroesListState.Data
                DiagonalSplitBackground {
                    Column {
                        // Header
                        LazyRow {
                            items(dataState.heroes) { hero ->
                                HeroItem(hero = hero) {
                                    onHeroClick(hero)
                                }
                            }
                        }
                    }
                }
            }
            is HeroesListState.Loading -> LoadingScreen()
            is HeroesListState.Error -> ErrorScreen()
        }
    }
}