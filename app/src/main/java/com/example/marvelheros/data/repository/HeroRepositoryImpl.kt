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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelAuth: MarvelAuth,
    private val localDataSource: LocalDataSource
) : HeroRepository {

    override fun observeHeroes(): Flow<List<Hero>> {
        return localDataSource.observeAllHeroes()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getHeroes(): MyResult<List<Hero>> {
        return safeApiCall(
            apiCall = {
                val ts = marvelAuth.getTimestamp()
                val hash = marvelAuth.generateHash(ts)
                val response = apiService.getHeroes(ts, marvelAuth.publicKey, hash)
                val sortedResult = response.data.results.sortedBy { it.name }
                val heroes = sortedResult.map { it.toDomain() }
                val entities = sortedResult.map { it.toEntity() }
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
                    ?: throw Exception("Hero not found")
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
