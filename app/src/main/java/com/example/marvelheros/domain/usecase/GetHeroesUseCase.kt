package com.example.marvelheros.domain.usecase

import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.domain.repository.HeroRepository
import com.example.marvelheros.utils.MyResult
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(
    private val repository: HeroRepository)
{
  suspend operator fun invoke(): MyResult<List<Hero>> = repository.getHeroes()
}