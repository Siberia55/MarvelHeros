package com.example.marvelheros.ui.navigation

sealed class Screen(val route: String) {
    object HeroList : Screen("hero_list")
    object HeroDetails : Screen("hero_details/{heroId}") {
        fun createRoute(heroId: Int) = "hero_details/$heroId"
    }
}