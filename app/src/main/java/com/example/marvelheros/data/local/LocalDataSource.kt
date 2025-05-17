package com.example.marvelheros.data.local

import com.example.marvelheros.data.local.dao.HeroDao
import com.example.marvelheros.data.local.entity.HeroEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val heroDao: HeroDao) {
    suspend fun getAllHeroes(): List<HeroEntity> {
        return heroDao.getAllHeroes()
    }

    suspend fun insertHeroes(heroes: List<HeroEntity>) {
        heroDao.insertHeroes(heroes)
    }

    suspend fun getHeroById(id: Int): HeroEntity? {
        return heroDao.getHeroById(id)
    }

    suspend fun insertHero(hero: HeroEntity) {
        heroDao.insertHero(hero)
    }
}