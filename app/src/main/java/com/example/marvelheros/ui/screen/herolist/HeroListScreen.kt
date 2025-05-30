package com.example.marvelheros.ui.screen.herolist

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvelheros.ui.navigation.Screen
import com.example.marvelheros.ui.screen.MainContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import coil3.imageLoader
import coil3.request.ImageRequest
import com.example.marvelheros.ui.components.ErrorView
import com.example.marvelheros.ui.components.LoadingView
import com.example.marvelheros.utils.ErrorCode

@Composable
fun HeroListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HeroListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) { viewModel.loadHeroes() }
    LaunchedEffect(state.heroes) {
        state.heroes.forEach { hero ->
            val request = ImageRequest.Builder(context)
                .data(hero.imageUrl)
                .build()
            context.imageLoader.enqueue(request)
        }
    }
    val safeModifier = modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars)
    when {
        state.isLoading -> LoadingView(modifier = modifier)
        state.error != null -> ErrorView(
            errorCode = ErrorCode.UNKNOWN_ERROR ,
            errorMessage = state.error,
            onRetry = { viewModel.loadHeroes() },
            modifier = modifier
        )

        else -> MainContent(
            heroes = state.heroes,
            onHeroClick = { hero ->
                navController.navigate(Screen.HeroDetails.createRoute(hero.id))
            },
            modifier = safeModifier
        )
    }
}