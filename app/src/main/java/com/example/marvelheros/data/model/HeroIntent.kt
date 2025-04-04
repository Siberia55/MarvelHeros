package com.example.marvelheros.data.model

sealed interface HeroIntent {
    object LoadHeroes : HeroIntent       // Загрузка данных
    data class SelectHero(val hero: Hero) : HeroIntent  // Выбор героя
    object DeselectHero : HeroIntent     // Сброс выбора
}