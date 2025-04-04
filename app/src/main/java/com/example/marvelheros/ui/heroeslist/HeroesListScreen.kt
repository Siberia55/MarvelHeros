/*package com.example.marvelheros.ui.heroeslist

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.marvelheros.data.main.MainContent
//import com.example.marvelheros.HeroItem
import com.example.marvelheros.ui.screen.HeroEvent
import com.example.marvelheros.ui.screen.HeroViewModel

/*
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

 */
@Composable
fun HeroListScreen(viewModel: HeroViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        CircularProgressIndicator()
    } else if (uiState.errorMessage != null) {
        ErrorScreen(message = uiState.errorMessage)
    } else {
        MainContent(
            heroes = uiState.heroes
        ) { hero ->
            viewModel.onEvent(HeroEvent.HeroSelected(hero))
        }
    }
}

@Composable
fun ErrorScreen(message: errorMessage) {
    TODO("Not yet implemented")
}

annotation class errorMessage

*/
