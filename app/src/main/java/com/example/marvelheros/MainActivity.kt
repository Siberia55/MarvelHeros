package com.example.marvelheros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.marvelheros.ui.theme.MarvelHerosTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Brush
//import com.example.marvelheros.data.main.MainScreen


import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.ui.screen.HeroEvent
import com.example.marvelheros.ui.screen.HeroViewModel
import com.example.marvelheros.ui.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

//----------

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HeroViewModel by viewModels() // Создаём ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.uiState.collectAsState().value
            MainScreen(
                state = state,//viewModel.uiState.collectAsState().value, // Передаём состояние
                onHeroClick = { hero -> viewModel.onEvent(HeroEvent.HeroSelected(hero)) },// Передаём обработчик клика
                onDismissHero = { viewModel.onEvent(HeroEvent.DismissHero) }
            )
        }
    }
}


           /*
            setContent {
                MarvelHerosTheme {
                    val viewModel: HeroViewModel = viewModels()
                    val state by viewModel.uiState.collectAsState()

                    MainScreen(
                        state = state,
                        onHeroClick = { hero -> viewModel.onEvent(HeroEvent.HeroSelected(hero)) },
                        onRetry = { viewModel.loadHeroes() }
                    )
                }
            }
        }
    }
}
*/








/*   13:30 04.04
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MarvelHerosTheme {
                MainScreen()
            }
        }
    }
}
*/
//---------- ниже первая лаба
/*
data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
)

 */
/*
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
    ),
    Hero(
        id = 3,
        name = "Haley Quinn",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOTzkRwd_h8AYu__LcSNij7TKsgCjDzQ-a4A&s",
        description = "Hi, I'm Harley Quin"
    )


)*/
/*
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
*/
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelHerosTheme {
                var selectedHero by remember { mutableStateOf<Hero?>(null) }
                LaunchedEffect (Unit){ /*lazyListState.scrollToItem(0)*/ }

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

            }
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
    //------
   /* {
    heroes.forEach { hero ->
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(hero.imageUrl).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.2f),
            contentScale = ContentScale.Crop
        )
    }
    //------
*/
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
                    .background(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Choose your hero",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White//MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(80.dp))
            val lazyListState = rememberLazyListState()
            val snapLayoutInfoProvider = remember(lazyListState) {
                SnapLayoutInfoProvider(
                    lazyListState = lazyListState,
                    snapPosition = SnapPosition.Center
                )
            }

            //val lazyListState = rememberLazyListState()
            // val snapLayoutInfoProvider = remember(lazyListState) {
            //    SnapLayoutInfoProvider(lazyListState)
            //}
           val snapFlingBehavior = rememberSnapFlingBehavior(snapLayoutInfoProvider)
            // val lazyListState = rememberLazyListState()
            LaunchedEffect(Unit) {
                lazyListState.scrollToItem(index = 10)
            }
            LazyRow(
                state = lazyListState,
flingBehavior = snapFlingBehavior,
                modifier = Modifier
                    .fillMaxSize(),
                    //.height(450.dp),

               // horizontalArrangement = Arrangement.spacedBy(16.dp),
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
                .height(400.dp),
contentAlignment = Alignment.Center // центровка изображения
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = hero.name,
                modifier = Modifier
                   // .width(250.dp)
                    .fillMaxSize(0.9f) // 90% ширины
                    .aspectRatio(0.7f) // пропорции
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
                .background(Color.Black)//(Color.Transparent)
        ) { // фон на весть экран
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Gradient background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.1f)// gradient level
                            ),
                            startY = 0.3f,
                            endY = 1f
                        )
                    )
            )

            // Button back
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        // Text up on image
        Column(
            modifier = Modifier
               .fillMaxSize()
               .padding(32.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = hero.name,
                style = MaterialTheme.typography.displayLarge.copy(
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
       // }
        //Hero

       // Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = hero.description,
            style = MaterialTheme.typography./*headlineMedium.*/
                bodyLarge.copy(
                color = Color.White,
           // ),
           //   modifier = Modifier.padding(24.dp)/*
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(2f, 2f),
                blurRadius = 4f
            )
       ),

        modifier = Modifier.padding(bottom = 32.dp)
        )
        }
    }
       // )
       // }

*/