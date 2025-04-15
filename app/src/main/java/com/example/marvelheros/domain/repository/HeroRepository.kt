package com.example.marvelheros.domain.repository

import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.utils.MyResult

interface HeroRepository {
    suspend fun getHeroes(): MyResult<List<Hero>>
    suspend fun getHeroById(heroId: Int): MyResult<Hero>
}