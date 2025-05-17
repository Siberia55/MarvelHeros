package com.example.marvelheros.di

import androidx.room.Room
import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.local.db.AppDatabase
import com.example.marvelheros.data.repository.HeroRepositoryImpl
import com.example.marvelheros.domain.usecase.GetHeroesUseCase
import com.example.marvelheros.utils.MarvelAuth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import android.content.Context
import com.example.marvelheros.data.local.LocalDataSource
import com.example.marvelheros.data.local.dao.HeroDao
import com.example.marvelheros.domain.repository.HeroRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMarvelAuth(): MarvelAuth = MarvelAuth()

    @Provides
    @Singleton
    fun provideHeroRepository(
        apiService: ApiService,
        marvelAuth: MarvelAuth,
        localDataSource: LocalDataSource
    ): HeroRepository {
        return HeroRepositoryImpl(apiService, marvelAuth, localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetHeroesUseCase(repository: HeroRepository): GetHeroesUseCase {
        return GetHeroesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "marvel_heroes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(database: AppDatabase): HeroDao {
        return database.heroDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSource(appDatabase.heroDao())
    }
}
