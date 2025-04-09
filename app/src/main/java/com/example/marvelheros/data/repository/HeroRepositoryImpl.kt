/*package com.example.marvelheros.data.repository

import com.example.marvelheros.data.model.Hero
import javax.inject.Inject


/*
interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}

class HeroRepositoryImpl @Inject constructor() : HeroRepository {
    override suspend fun getHeroes(): List<Hero> = heroes // Ваш существующий список
}
*/

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}

class HeroRepositoryImpl @Inject constructor() : HeroRepository {
    override suspend fun getHeroes(): List<Hero> = listOf(
        Hero(1, "Deadpool", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s", "Hi, I'm Deadpool"),
        Hero(2, "Iron Man", "https://www.specfictionshop.com/cdn/shop/products/315455127_2253071438203857_6311282012262232749_n_2000x.jpg?v=1669836598", "Hi, I'm Iron Man"),
        Hero(3, "Harley Quinn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOTzkRwd_h8AYu__LcSNij7TKsgCjDzQ-a4A&s", "Hi, I'm Harley Quinn")
    )
}


/*
class HeroRepository(private val apiService: ApiService) {

    // Кэш для хранения данных (опционально)
    private var cachedHeroes: List<Hero>? = null

    // Получение героев
    suspend fun getHeroes(): Result<List<Hero>> {
        return try {
            // Проверяем кэш
            if (cachedHeroes != null) {
                Result.success(cachedHeroes!!)
            } else {
                // Делаем запрос к API
                val heroes = withContext(Dispatchers.IO) {
                    apiService.getAllHeroes()
                }
                // Сохраняем в кэш
                cachedHeroes = heroes
                Result.success(heroes)
            }
        } catch (e: Exception) {
            Result.failure(e) // Возвращаем ошибку
        }
    }
}
class HeroRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getHeroes(): Result<List<Hero>> {
        return try {
            Result.success(apiService.getHeroes())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

 */
 */
package com.example.marvelheros.data.repository

import android.R.attr.apiKey
import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.utils.MarvelAuth
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
    //suspend fun getHeroById(id: Int): Hero
}

class HeroRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val marvelAuth: MarvelAuth
) : HeroRepository {
    override suspend fun getHeroes(): List<Hero> {
       /*val ts = marvelAuth.getTimestamp()
        val hash = marvelAuth.generateHash(ts)
        val apiKey = marvelAuth.publicKey
        return try {
            apiService.getHeroes(ts, apiKey, hash)
                .data.results
                .map { it.toHero() }
        } catch (e: Exception) {
            //getMockHeroes()
            emptyList<Hero>()
        }*/
        return getMockHeroes()
    }
}

    private fun getMockHeroes() = listOf(
        Hero(1, "Deadpool", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s", "Hi, I'm Deadpool"),
        Hero(2, "Iron Man", "https://www.specfictionshop.com/cdn/shop/products/315455127_2253071438203857_6311282012262232749_n_2000x.jpg?v=1669836598", "Hi, I'm Iron Man"),
       // Hero(3, "Harley Quinn", "https://...", "Hi, I'm Harley Quinn")
    )

