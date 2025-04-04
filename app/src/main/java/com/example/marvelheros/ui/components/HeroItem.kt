package com.example.marvelheros.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.marvelheros.data.model.Hero

//data class HeroItem(val hero: Hero, val onClick: () -> Unit)

@Composable
fun HeroItem(hero: Hero, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .width(300.dp)  // Ограничиваем ширину карточки
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            AsyncImage(
                model = hero.imageUrl,
                contentDescription = hero.name,
                modifier = Modifier
                    .size(400.dp)
                    .padding(10.dp)
            )
            Text(
                text = hero.name,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}