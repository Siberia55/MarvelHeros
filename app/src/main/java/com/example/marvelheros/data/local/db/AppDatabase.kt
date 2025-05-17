package com.example.marvelheros.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelheros.data.local.dao.HeroDao
import com.example.marvelheros.data.local.entity.HeroEntity

@Database(entities = [HeroEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}
