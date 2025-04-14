package com.example.marvelheros.domain.repository

import com.example.marvelheros.domain.model.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}