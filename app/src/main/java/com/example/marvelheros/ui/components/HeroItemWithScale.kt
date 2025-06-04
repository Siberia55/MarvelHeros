package com.example.marvelheros.ui.components

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.marvelheros.domain.model.Hero
import kotlin.math.abs
import com.example.marvelheros.ui.theme.diments.OtherConstants

@Composable
fun HeroItemWithScale(
    hero: Hero,
    index: Int,
    lazyListState: LazyListState,
    onHeroClick: (Hero, Int) -> Unit,

    modifier: Modifier = Modifier

) {

    val itemInfo by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
        }
    }
    val scale = remember(itemInfo) {//val scale by remember(itemInfo) {
        derivedStateOf {
            itemInfo?.let {
                val viewportWidth = lazyListState.layoutInfo.viewportEndOffset -
                        lazyListState.layoutInfo.viewportStartOffset
                val centerOffset = lazyListState.layoutInfo.viewportStartOffset + viewportWidth / 2
                val itemCenter = it.offset + it.size / 2
                val distanceFromCenter = abs(itemCenter - centerOffset).toFloat()
                val maxDistance = (viewportWidth / 2).toFloat()
                val fraction = (distanceFromCenter / maxDistance).coerceIn(0f, 1f)
                1f - 0.5f * fraction
            } //?: 1f
        }
    }
/*
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(
            durationMillis = OtherConstants.DURATION_OF_ACTION,
            easing = LinearOutSlowInEasing
        ),
        label = "hero-scale"
    )
*/
    val animatedScale = scale.value?.let { target ->
        animateFloatAsState(
            targetValue = target,
            animationSpec = tween(
                durationMillis = OtherConstants.DURATION_OF_ACTION,
                easing = LinearOutSlowInEasing
            ),
            label = "hero-scale"
        )
    }?.value ?: 0.9f


    Box(
        modifier = Modifier //modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
    ) {
        HeroItem(
            hero = hero,
            onClick = { onHeroClick(hero, index) }
        )
    }
}