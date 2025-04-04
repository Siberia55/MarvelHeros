package com.example.marvelheros.ui.screen

import com.example.marvelheros.data.model.Hero

sealed class HeroEvent {
    data object LoadHeroes : HeroEvent()
    data class SelectHero(val hero: Hero) : HeroEvent()
    data object DismissHero : HeroEvent()
}