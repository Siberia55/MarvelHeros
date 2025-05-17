/*package com.example.marvelheros.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.components.FullScreenHeroDetails
import com.example.marvelheros.ui.components.LocalDirectionWrapper
import com.example.marvelheros.ui.theme.diments.DimensProgressIndicator


@Composable
fun MainScreen(
    state: HeroUiState,
    onHeroClick: (Hero) -> Unit,
    onDismissHero: () -> Unit,
    StrokeCap: Any
) {
    LocalDirectionWrapper {
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(DimensProgressIndicator.sizeMax),
                    strokeWidth = DimensProgressIndicator.strokeWidthSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )

            } else if (state.errorMessage != null) {
                val errorText = stringResource(id = state.errorMessage)
                val fullError = state.serverErrorMessage?.let { "$errorText: $it" } ?: errorText
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = fullError,
                    fontSize = 30.sp
                )
                //
            } else if (state.selectedHero != null) {
                if(state.heroDetailsLoading) {
                    CircularProgressIndicator()
                }
                else if (state.heroDetailsError != null){
                val errorText = stringResource(id = state.heroDetailsError )
                val fullError = state.serverErrorMessage?.let { "$errorText: $it" } ?: errorText
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = fullError,
                    fontSize = 20.sp
                )
                } else {
                    FullScreenHeroDetails(hero = state.selectedHero, onDismiss = onDismissHero)
                }
                } else {
                    MainContent(heroes = state.heroes, onHeroClick = onHeroClick)
                }
            }
        }
    }

*/

