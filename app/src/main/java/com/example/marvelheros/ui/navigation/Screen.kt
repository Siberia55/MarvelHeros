package com.example.marvelheros.ui.navigation

sealed class Screen(val route: String) {
    object HeroList : Screen("hero_list")
    object HeroDetails : Screen("hero_details/{heroId}") {
        fun createRoute(heroId: Int) = "hero_details/$heroId"
    }/*
   object ErrorScreen: Screen("error_screen/{code}/{message}") {
        fun createRoute(code: Int, message: String): String {
            val encodedMessage = java.net.URLEncoder.encode(message,"UTF-8")
            return  "error_screen/$code/$encodedMessage"
        }
    }*/
}