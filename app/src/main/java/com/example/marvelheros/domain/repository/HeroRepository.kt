package com.example.marvelheros.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.utils.MyResult

interface HeroRepository {
    fun observeHeroes(): Flow<List<Hero>>
    suspend fun getHeroes(): MyResult<List<Hero>>
    suspend fun getHeroById(heroId: Int): MyResult<Hero>
}