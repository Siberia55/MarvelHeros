package com.example.marvelheros.ui.screen.herolist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvelheros.ui.navigation.Screen
import com.example.marvelheros.ui.screen.MainContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.example.marvelheros.ui.components.ErrorView
import com.example.marvelheros.ui.components.LoadingView

@Composable
fun HeroListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HeroListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) { viewModel.loadHeroes() }
    when {
        state.isLoading -> LoadingView(modifier = modifier)
        state.error != null -> ErrorView(
            errorMessage = state.error,
            onRetry = { viewModel.loadHeroes() },
            modifier = modifier
        )

        else -> MainContent(
            heroes = state.heroes,
            onHeroClick = { hero ->
                navController.navigate(Screen.HeroDetails.createRoute(hero.id))
            },
            modifier = modifier
        )
    }
}