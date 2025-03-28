package com.example.marvelheros.di

import com.example.marvelheros.data.api.ApiService
import com.example.marvelheros.data.repository.HeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
/*
@Module
@InstallIn(SingletonComponent::class) // Модуль доступен везде в приложении
object AppModule {

    // 1. Создаем Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    // 2. Создаем ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    // 3. Создаем HeroRepository
    @Provides
    @Singleton
    fun provideHeroRepository(apiService: ApiService): HeroRepository =
        HeroRepository(apiService)
}

 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}