package com.example.marvelheros.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marvelheros.R
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.components.HeroItem
//import com.example.marvelheros.ui.components.diagonalSplit

/*
@Composable
fun MainContent(
    heroes: List<Hero>,
    onHeroClick: (Hero) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(heroes) { hero ->
            HeroItem(
                hero = hero,
                onClick = { onHeroClick(hero) }
            )
        }
    }
}
*/
/*
@Composable
fun MainContent(
    heroes: List<Hero>,  // Принимаем List<Hero>
    onHeroClick: (Hero) -> Unit
) {
    LazyRow {
        items(
            items = heroes,
            key = { it.id }  // Используем ID как ключ
        ) { hero ->
            HeroItem(
                hero = hero,
                onClick = { onHeroClick(hero) }
            )
        }
    }
}

 */
@SuppressLint("SuspiciousModifierThen")
fun Modifier.diagonalSplit(color1: Color, color2: Color): Modifier = this.then(
    drawBehind {
        val width = size.width
        val height = size.height
        drawRect(color = color2)
// разделение экрана
        val path =Path().apply {
            moveTo(0f, height)  //левый нижний угол //(0f, 0f) начало правый верхний угол
            lineTo(width, 0f)   //линия к правому верхнему углу //(width, height)
            lineTo(0f, 0f)      //замыкание треугольника
            close()
        }
        drawPath(path = path, color = color1)
    }
)

@Composable

fun MainContent(
    heroes: List<Hero>,
    onHeroClick: (Hero) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .diagonalSplit(
                color1 = Color.DarkGray,
                color2 = Color.Red
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .padding(top = 20.dp)
                    .background(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Choose your hero",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(80.dp))

            val lazyListState = rememberLazyListState()
            val snapLayoutInfoProvider = remember(lazyListState) {
                SnapLayoutInfoProvider(
                    lazyListState = lazyListState,
                    snapPosition = SnapPosition.Center
                )
            }
            val snapFlingBehavior = rememberSnapFlingBehavior(snapLayoutInfoProvider)

            LaunchedEffect(Unit) {
                lazyListState.scrollToItem(index = 0)
            }

            LazyRow(
                state = lazyListState,
                flingBehavior = snapFlingBehavior,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(heroes) { hero ->
                    HeroItem(
                        //uiState.selectedHero?.let { hero ->
                        //  FullScreenHeroDetails(
                        hero = hero,
                        //      onDismiss = onDismissHero
                        onClick = { onHeroClick(hero) }
                    )

                }

                }
            }
        }
    }
