package com.example.marvelheros.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelheros.R
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.components.HeroItem
import com.example.marvelheros.ui.theme.diments.Dimens
import kotlin.math.abs
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity


@SuppressLint("SuspiciousModifierThen")
fun Modifier.diagonalSplit(color1: Color, color2: Color): Modifier = this.then(
    drawBehind {
        val width = size.width
        val height = size.height
        drawRect(color = color2)
        val path = Path().apply {
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
    onHeroClick: (Hero) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val lazyListState = rememberLazyListState()
    LaunchedEffect(configuration.orientation) {
        lazyListState.scrollToItem(lazyListState.firstVisibleItemIndex)//(0)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .diagonalSplit(
                color1 = MaterialTheme.colorScheme.background,//Color.DarkGray,
                color2 = MaterialTheme.colorScheme.primary//Color.Red
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.paddingLarge)
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .width(Dimens.widthMedium)
                    .height(Dimens.heightMedium)
                    .padding(top = Dimens.paddingLarge)
                    .background(MaterialTheme.colorScheme.background)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.choose_hero),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(Dimens.heightMedium))
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
                modifier = Modifier.padding(horizontal = Dimens.paddingMedium),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                /* items(heroes) {hero ->


                 */
                itemsIndexed(heroes) { index, hero ->
                    //val layoutInfo = lazyListState.layoutInfo
                    //val itemInfo = lazyListState.layoutInfo.visibleItemsInfo
                    val viewportSizePx = with(density) {
                        configuration.screenWidthDp.dp.roundToPx()
                    }
                    val itemInfo by remember {
                        derivedStateOf {
                            lazyListState.layoutInfo.visibleItemsInfo
                                .firstOrNull { it.index == index }
                        }
                    }
                    val viewportWidth =
                        lazyListState.layoutInfo.viewportEndOffset - lazyListState.layoutInfo.viewportStartOffset
                    val centerOffset =
                        lazyListState.layoutInfo.viewportStartOffset + viewportWidth / 2
                    // val center = with(LocalDensity.current) {
                    //    LocalConfiguration.current.screenWidthDp.dp.toPx() / 2
                    // }
                    //val listOffset = layoutInfo.viewportStartOffset
                    // val scaleTarget = remember(itemInfo) {
                    val scale by remember(itemInfo) {
                        derivedStateOf {
                            itemInfo?.let {
                                val itemCenter = it.offset + it.size / 2// - listOffset
                                //val distance = kotlin.math.abs(center - itemCenter)
                                val distanceFromCenter = abs(itemCenter - centerOffset).toFloat()
                                val maxDistance = (viewportWidth / 2).toFloat()
                                //val maxDistance = center
                                val fraction = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)
                                //val fraction = /*1f -*/ (distance / maxDistance).coerceIn(0f, 1f)
                                1f - 0.3f * fraction // scale от 0.85 до 1.0
                            } ?: 0.7f
                        }
                    }
                    val animatedScale by animateFloatAsState(
                        targetValue = scale,//scaleTarget.value,
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = LinearOutSlowInEasing
                        ),
                        label = "hero-scale"
                    )
                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                scaleX = animatedScale
                                scaleY = animatedScale
                            }
                    )
                    {
                        HeroItem(
                            hero = hero,
                            onClick = { onHeroClick(hero) },

                            )
                    }
                }
            }
        }
    }
}
