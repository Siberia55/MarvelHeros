package com.example.marvelheros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.marvelheros.ui.components.LocalDirectionWrapper
import com.example.marvelheros.ui.screen.HeroEvent
import com.example.marvelheros.ui.screen.HeroViewModel
//import com.example.marvelheros.ui.screen.MainScreen
import com.example.marvelheros.ui.theme.MarvelHerosTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.marvelheros.ui.navigation.NavGraph
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.marvelheros.ui.components.EdgeToEdgeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val viewModel: HeroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
                MarvelHerosTheme {
                    EdgeToEdgeTheme {
                        LocalDirectionWrapper {

                            /*val state = viewModel.uiState.collectAsState().value
                        MainScreen(
                            state = state,
                            onHeroClick = { hero -> viewModel.onEvent(HeroEvent.HeroSelected(hero)) },
                            onDismissHero = { viewModel.onEvent(HeroEvent.DismissHero) },
                            StrokeCap = StrokeCap
                        )
                     */
                            //-------------------
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                val navController = rememberNavController()
                                NavGraph(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }


