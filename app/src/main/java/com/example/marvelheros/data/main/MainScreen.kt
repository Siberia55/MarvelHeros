package com.example.marvelheros.data.main

import androidx.compose.runtime.Composable
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.ui.components.FullScreenHeroDetails
import com.example.marvelheros.ui.screen.HeroUiState


//-----gpt

/*@Composable
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
*/
@Composable
fun MainScreen(state: HeroUiState,
               onHeroClick: (Hero) -> Unit,
               onDismissHero:() -> Unit) {
   // var selectedHero by remember { mutableStateOf<Hero?>(null) }

    if (state.selectedHero != null) {
        FullScreenHeroDetails(hero = state.selectedHero,
            onDismiss = onDismissHero)//{ selectedHero = null
        //onDismissHero()})
    } else {
        MainContent(heroes = state.heroes,
           onHeroClick = onHeroClick)
    }
}
//------DS
