package com.example.marvelheros.ui.screen.herodetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvelheros.ui.components.FullScreenHeroDetails
import com.example.marvelheros.ui.screen.herolist.HeroListViewModel
import androidx.compose.runtime.getValue
import com.example.marvelheros.ui.components.LoadingView
import com.example.marvelheros.ui.components.ErrorView



@Composable
fun HeroDetailScreen(
    heroId : Int,
    onBackClick : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HeroDetailViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(heroId) {
        viewModel.loadHero(heroId)
    }
    when{
        state.isLoading -> LoadingView(modifier)
        state.error != null -> ErrorView(
            errorMessage = state.error,
            onRetry = {viewModel.loadHero(heroId ) },
            modifier = modifier
        )
        state.hero != null -> FullScreenHeroDetails(
            hero = state.hero!!,
            onDismiss = onBackClick,
            modifier = modifier
        )
    }
}