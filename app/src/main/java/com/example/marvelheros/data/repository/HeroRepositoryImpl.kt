package com.example.marvelheros.data.repository

import android.util.Log
import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.local.LocalDataSource
import com.example.marvelheros.data.mapper.HeroMapper.toDomain
import com.example.marvelheros.data.mapper.HeroMapper.toEntity
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.utils.MarvelAuth
import javax.inject.Inject
import com.example.marvelheros.utils.MyResult

interface HeroRepository {
    suspend fun getHeroes(): MyResult<List<Hero>>
    suspend fun getHeroById(heroId: Int): MyResult<Hero>
}
class HeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelAuth: MarvelAuth,
    private val localDataSource: LocalDataSource
    ) : HeroRepository {

    override suspend fun getHeroes(): MyResult<List<Hero>> {
        return try {
// из БД
            val localHeroes = localDataSource.getAllHeroes().map { it.toDomain() }
            if (localHeroes.isNotEmpty()) {
                return MyResult.Success(localHeroes)
            }
//запрос из сети
            val ts = marvelAuth.getTimestamp()
            val hash = marvelAuth.generateHash(ts)
            val response = apiService.getHeroes(
                ts, marvelAuth.publicKey, hash
            )
            val heroes = response.data.results.map { it.toDomain() }
            val entities = response.data.results.map { it.toEntity() }
            localDataSource.insertHeroes(entities)
            MyResult.Success(heroes)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("HeroRepository", "Ошибка загрузки героев: ${e.message}")
            val fallback = localDataSource.getAllHeroes().map { it.toDomain() }
            if (fallback.isNotEmpty()) {
                MyResult.Success(fallback)
            } else
                MyResult.Error("Ошибка загрузки: ${e.localizedMessage ?: "Unknown error"}")

        }
    }
    override suspend fun getHeroById(heroId: Int): MyResult<Hero> {
        return try {
            val localHero = localDataSource.getHeroById(heroId)
            if (localHero != null) {
                return MyResult.Success(localHero.toDomain())
            }
            val ts = marvelAuth.getTimestamp()
            val hash = marvelAuth.generateHash(ts)
            val response = apiService.getHeroById(heroId, ts, marvelAuth.publicKey, hash)
            val heroDto = response.data.results.firstOrNull()

            if (heroDto != null) {
                val entity = heroDto.toEntity()
                localDataSource.insertHero(entity)
                MyResult.Success(entity.toDomain())
            } else {
                MyResult.Error("Герой не найден")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MyResult.Error("Ошибка загрузки героя: ${e.localizedMessage ?: "Unknown error"}")
        }
    }
}