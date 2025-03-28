package com.example.marvelheros.data.repository

import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.model.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

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