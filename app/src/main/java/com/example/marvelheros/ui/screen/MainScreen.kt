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


/*
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.errorMessage != null -> {
                ErrorMessage(
                    message = state.errorMessage,
                    onRetry = onRetry,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                MainContent(
                    heroes = state.heroes,
                    onHeroClick = onHeroClick
                )
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Повторить попытку")
        }
    }
}*/