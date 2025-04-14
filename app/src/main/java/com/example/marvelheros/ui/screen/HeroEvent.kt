package com.example.marvelheros.ui.screen

import com.example.marvelheros.data.model.Hero


sealed class HeroEvent {
    data class HeroSelected(val hero: Hero) : HeroEvent()
    data object LoadHeroes : HeroEvent()
    data object DismissHero : HeroEvent()
    //data object HeroClick: HeroEvent()
    //object Retry : HeroEvent()
}


