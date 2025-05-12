package com.example.marvelheros.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.example.marvelheros.ui.screen.herolist.HeroListScreen
import com.example.marvelheros.ui.screen.herodetail.HeroDetailScreen


@Composable
fun NavGraph(navController: NavHostController,
modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
            startDestination = Screen.HeroList.route,
            modifier = modifier){
        composable (route = Screen.HeroList.route){
            HeroListScreen(navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route = Screen.HeroDetails.route,
            arguments = listOf(navArgument("heroId"){
                type = NavType.IntType })
        )
         { backStackEntry ->
                val heroId = backStackEntry.arguments?.getInt("heroId") ?: 0
           HeroDetailScreen(
               heroId = heroId,
               onBackClick = {navController.popBackStack()},
               modifier = Modifier.fillMaxSize()
            )
        }
    }
}
