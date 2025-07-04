package com.example.marvelheros

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.marvelheros.ui.components.LocalDirectionWrapper
import com.example.marvelheros.ui.theme.MarvelHerosTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.marvelheros.ui.navigation.NavGraph
import androidx.core.view.WindowCompat
import com.example.marvelheros.ui.components.EdgeToEdgeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DisposableEffect(Unit) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
                onDispose {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
            MarvelHerosTheme {
                EdgeToEdgeTheme {
                    LocalDirectionWrapper {
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




