package com.example.marvelheros.ui.components

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.marvelheros.R
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.theme.diments.Dimens
import com.example.marvelheros.ui.theme.diments.Spaced
import com.example.marvelheros.utils.findCenteredItemIndex

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
    onHeroClick: (Hero, Int) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration.orientation) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            listState.findCenteredItemIndex()?.let {
                listState.scrollToItem(it)
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .diagonalSplit(
                color1 = MaterialTheme.colorScheme.background,
                color2 = MaterialTheme.colorScheme.primary
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeGestures
                        .only(WindowInsetsSides.Top + WindowInsetsSides.Bottom)
                )
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
            Spacer(modifier = Modifier.height(Dimens.heightSmall))
            Text(
                text = stringResource(R.string.choose_hero),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(Dimens.heightMedium))

            val snapLayoutInfoProvider = remember(listState) {
                SnapLayoutInfoProvider(
                    lazyListState = listState,
                    snapPosition = SnapPosition.Center
                )
            }
            val snapFlingBehavior = rememberSnapFlingBehavior(snapLayoutInfoProvider)

            LazyRow(
                state = listState,
                flingBehavior = snapFlingBehavior,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = Dimens.paddingLarge
                ),
                horizontalArrangement = Arrangement.spacedBy(Spaced.large),
            ) {
                itemsIndexed(heroes) { index, hero ->
                    HeroItemWithScale(
                        hero = hero,
                        index = index,
                        lazyListState = listState,
                        onHeroClick = onHeroClick,
                    )
                }
            }
        }
    }
}