package com.example.marvelheros.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marvelheros.data.local.entity.HeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: HeroEntity)

    @Query("SELECT * FROM heroes WHERE id = :heroId")
    suspend fun getHeroById(heroId: Int): HeroEntity?

    @Query("SELECT * FROM heroes ORDER BY name ASC")
     fun observeAllHeroes(): Flow<List<HeroEntity>>

    @Query("SELECT * FROM heroes ORDER BY name ASC")
    suspend fun getAllHeroes(): List<HeroEntity>

    @Update
    suspend fun updateHero(hero: HeroEntity)
}
