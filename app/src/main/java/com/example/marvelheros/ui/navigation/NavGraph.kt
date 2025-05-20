package com.example.marvelheros.ui.navigation

import android.R.attr.type
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.example.marvelheros.ui.screen.herolist.HeroListScreen
import com.example.marvelheros.ui.screen.herodetail.HeroDetailScreen

//import com.example.marvelheros.ui.screen.ErrorScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HeroList.route,
        modifier = modifier
    ) {
        composable(route = Screen.HeroList.route) {
            HeroListScreen(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars)
            )
        }
        composable(
            route = Screen.HeroDetails.route,
            arguments = listOf(
                navArgument("heroId") {
                    type = NavType.IntType
                })
        )
        { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId") ?: 0
            HeroDetailScreen(
                heroId = heroId,
                onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars)
            )
        }
        /*   composable(
               route = Screen.ErrorScreen.route,
               arguments = listOf(
                   navArgument("code") {type = NavType.IntType},
                   navArgument("message"){type = NavType.StringType}
               )
           )
           {backStackEntry ->
               val code = backStackEntry.arguments?. getInt("code")?: -1
               val message = backStackEntry.arguments?.getString("message")?: "Unknown error"
            /*   ErrorScreen(
                   code = code,
                   message = message,
                   onBackClick = {navController.popBackStack()}
               )*/

          */
    }
}
//}
