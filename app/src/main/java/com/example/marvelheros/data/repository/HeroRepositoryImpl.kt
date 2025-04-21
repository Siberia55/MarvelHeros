package com.example.marvelheros.data.repository

import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.local.LocalDataSource
import com.example.marvelheros.data.mapper.HeroMapper.toDomain
import com.example.marvelheros.data.mapper.HeroMapper.toEntity
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.domain.repository.HeroRepository
import com.example.marvelheros.utils.MarvelAuth
import javax.inject.Inject
import com.example.marvelheros.utils.MyResult
import com.example.marvelheros.utils.safeApiCall

class HeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelAuth: MarvelAuth,
    private val localDataSource: LocalDataSource
    ) : HeroRepository {

    override suspend fun getHeroes(): MyResult<List<Hero>> {
        return safeApiCall(
            apiCall = {
                val ts = marvelAuth.getTimestamp()
                val hash = marvelAuth.generateHash(ts)
                val response = apiService.getHeroes(ts, marvelAuth.publicKey, hash)
                val heroes = response.data.results.map { it.toDomain() }
                val entities = response.data.results.map { it.toEntity() }
                localDataSource.insertHeroes(entities)
                heroes
            },
            fallback = {
                val localHeroes = localDataSource.getAllHeroes().map { it.toDomain() }
                if (localHeroes.isNotEmpty()) localHeroes else null
            },
            errorTag = "HeroRepository"
        )
    }

    override suspend fun getHeroById(heroId: Int): MyResult<Hero> {
        return safeApiCall(
            apiCall = {
                val ts = marvelAuth.getTimestamp()
                val hash = marvelAuth.generateHash(ts)
                val response = apiService.getHeroById(heroId, ts, marvelAuth.publicKey, hash)
                val heroDto = response.data.results.firstOrNull()
                    ?: throw Exception("Герой не найден")
                val entity = heroDto.toEntity()
                localDataSource.insertHero(entity)
                entity.toDomain()
            },
            fallback = {
                val localHero = localDataSource.getHeroById(heroId)
                localHero?.toDomain()
            },
            errorTag = "HeroRepository"
        )
    }
}
