package com.example.marvelheros

import android.R.attr.path
import android.R.attr.top
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.Image
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.marvelheros.ui.theme.MarvelHerosTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment


data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
)

val heroes = listOf(
    Hero(
        id = 1,
        name = "Deadpool",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s",
        description = "Hi, I'm Deadpool"
    ),
    Hero(
        id = 2,
        name = "Iron Man",
        imageUrl = "https://www.specfictionshop.com/cdn/shop/products/315455127_2253071438203857_6311282012262232749_n_2000x.jpg?v=1669836598",
        description = "Hi, I'm Iron Man"
    )
)

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelHerosTheme {
                var selectedHero by remember { mutableStateOf<Hero?>(null) }
                Box(modifier = Modifier.fillMaxSize()){
                    MainContent(
                        onHeroClick = { /*hero -> */selectedHero = it }//onHeroClick = { hero -> selectedHero.value = hero }
                    )
                    selectedHero?.let {hero ->//selectedHero.value?.let { hero ->
                        FullScreenHero(
                            hero = hero,
                            onDismiss = { selectedHero = null }//{ selectedHero.value = null }
                        )

                    }
                }
                /* modifier = Modifier
                     .fillMaxSize()
                     .diagonalSplit(
                         color1 = Color.DarkGray,
                         color2 = Color.Red,
                     )*/
                //)
                /*    {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .padding(vertical = 10.dp)
                            )
                            Text(
                                text = "Choose your hero",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White
                            )
                            val lazyListState = rememberLazyListState()
                            val snapLayoutInfoProvider = remember(lazyListState){
                                SnapLayoutInfoProvider(lazyListState)
                            }
                            val snapFlingBehavior = rememberSnapFlingBehavior(snapLayoutInfoProvider)

                            Spacer(modifier = Modifier.height(100.dp))
                            LazyRow(
                                state = lazyListState,
                                flingBehavior = snapFlingBehavior,
                                modifier = Modifier.fillMaxWidth()
                                .height(450.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                items(heroes) { hero ->
                                    HeroItem(hero = hero)

                                }
                            }

                        }
                    }
    */            }
        }
    }
}
//ImageFromUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s")

@Composable // основной контент
fun MainContent(onHeroClick: (Hero) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .diagonalSplit(
                color1 = Color.DarkGray,
                color2 = Color.Red
            )
    )
    {
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
                    .background(Color.Green)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Choose your hero",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White//MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier. height(80.dp))
            val lazyListState = rememberLazyListState()
            val snapLayoutInfoProvider = remember(lazyListState) {
                SnapLayoutInfoProvider(lazyListState)
            }
            val snapFlingBehavior = rememberSnapFlingBehavior(snapLayoutInfoProvider)

            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .height(450.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(heroes) { hero ->
                    HeroItem(
                        hero = hero,
                        onClick = { onHeroClick(hero) }
                    )
                }
            }
        }
    }
}
// обновленный hero
@Composable
fun HeroItem(hero: Hero, onClick: () -> Unit) {
    Column( // Возвращаем Column вместо Box для правильного выравнивания
        modifier = Modifier
            .width(300.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(400.dp)
            // .padding(8.dp)
            // .clickable(onClick = onClick)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = hero.name,
                modifier = Modifier
                    .width(250.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                //text = hero.name,
                text = hero.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f

                    )
                ),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)

            )
        }
    }
}
    // Полный экран
@Composable
    fun FullScreenHero(hero: Hero, onDismiss: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.95f))
                .clickable(onClick = onDismiss)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                   // .padding(32.dp)
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(32.dp)
            ) {
                Text(
                    text = hero.name,
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = Color.White,
                        /*
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 8f

                         */

                        )
                    )
                //)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = hero.description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(1f, 1f),
                            blurRadius = 4f
                        )
                    ),
                    modifier = Modifier.background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
                        .padding(16.dp)
                )


            }
        }
    }


/* modifier = Modifier
     .padding(vertical = 5.dp)
     .width(220.dp)
     .height(50.dp)
     .background(Color.LightGray)
     .wrapContentSize(Alignment.Center)
 }

     )
}
)
}
@Composable
fun HeroItem(hero: Hero){
Column (
modifier = Modifier
.width(300.dp)
.padding(8.dp),
horizontalAlignment = Alignment.CenterHorizontally
) {
Box(
modifier = Modifier
 //.fillMaxWidth()
 .width(280.dp)
 .padding(10.dp),
//horizontalAlignment = Alignment.CenterHorizontally
) {
AsyncImage(
 model = ImageRequest.Builder(LocalContext.current)
     .data(hero.imageUrl)
     .crossfade(true)
     .build(),
 contentDescription = null,
 modifier = Modifier
     .width(300.dp)
     .height(400.dp)
     .clip(RoundedCornerShape(10.dp)),
 contentScale = ContentScale.Crop
)
Text(
 text = hero.name,
 style = MaterialTheme.typography.titleLarge.copy(
     color = Color.Green,
     shadow = Shadow(
         color = Color.Black,
         offset = Offset(2f, 2f),
         blurRadius = 4f
     )
 ),
 //color = Color.White,
 modifier = Modifier.padding(top = 5.dp)
     .align(Alignment.BottomStart)
)
}
}
}
/*fun ImageFromUrl(imageUrl: String) {
Box (
modifier = Modifier
.fillMaxSize()
.diagonalSplit(
color1 = Color.DarkGray,
color2 = Color.Red
)
)
Column(
modifier = Modifier
 .fillMaxSize()
 .padding(5.dp),
horizontalAlignment = Alignment.CenterHorizontally,

//verticalArrangement = Arrangement.Center
) {

Image(painter = painterResource(R.drawable.logo),
 contentDescription = null,
 modifier = Modifier
     .width(100.dp)
     .height(50.dp)
     .padding(top = 20.dp)
     .background(Color.Green))

Spacer(modifier = Modifier.height(1.dp))
Text(
 text = "Choose your hero",
 style = MaterialTheme.typography.headlineMedium,
 color = Color.White//MaterialTheme.colorScheme.onSurface
/* modifier = Modifier
     .padding(vertical = 5.dp)
     .width(220.dp)
     .height(50.dp)
     .background(Color.LightGray)
     .wrapContentSize(Alignment.Center)*/
)

Spacer(modifier = Modifier.height(150.dp))
AsyncImage(
 model = ImageRequest.Builder(LocalContext.current)
     .data (imageUrl)//("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s")
     .crossfade(true)
     .listener(
         onError = { _, throwable ->
             Log.e(
                 "AsyncImage",
                 "Error ${throwable.toString()}"
             )
         }
     )
     .build(),
 //placeholder = painterResource(R.drawable.placeholder),
 contentDescription = "Hero image",//stringResource(R.string.description),
 error = painterResource(R.drawable.error_connect),
 contentScale = ContentScale.Crop,
 modifier = Modifier
     .width(200.dp)
     .height(350.dp)
     .clip(RectangleShape)
     .background(Color.Blue)
)
     //.size(250.dp)
     // .clip(RoundedCornerShape(10.dp)
     //.width(20.dp)

}
}

*/

/*@Preview
@Composable
fun MarvelPriv(){
MarvelChoose("IronMan")
}*/