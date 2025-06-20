package com.example.marvelheros.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.theme.diments.Dimens
import com.example.marvelheros.ui.theme.diments.OtherConstants

@Composable
fun HeroItem(
    hero: Hero,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(OtherConstants.CARD_WIDTH_RATIO_LARGE)
            .aspectRatio(OtherConstants.CARD_ASPECT_RATIO_MEDIUM)
            .clip(RoundedCornerShape(OtherConstants.radiusShape))
            .clickable { onClick() }

    ) {
        AsyncImage(
            model = hero.imageUrl,
            contentDescription = hero.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(Dimens.paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Dimens.heightSmall))
            Text(
                text = hero.name,
                fontSize = OtherConstants.fontSize,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
