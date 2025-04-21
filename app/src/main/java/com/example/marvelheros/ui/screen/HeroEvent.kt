package com.example.marvelheros.ui.screen

import com.example.marvelheros.domain.model.Hero


sealed class HeroEvent {
    data class ShowError(val message: Int) : HeroEvent()
    data class HeroSelected(val hero: Hero) : HeroEvent()
    data object LoadHeroes : HeroEvent()
    data object DismissHero : HeroEvent()
}


