package com.example.marvelheros.ui.screen

import androidx.lifecycle.ViewModel
import com.example.marvelheros.data.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HeroViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        HeroUiState(
            heroes = getMockHeroes() // Передаём список героев
        )
    )
    val uiState: StateFlow<HeroUiState> get() = _uiState // Теперь это поток, а не просто объект

    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.HeroSelected -> {
                _uiState.update { it.copy(selectedHero = event.hero) }
            }

            HeroEvent.DismissHero -> {
                _uiState.update { it.copy(selectedHero = null) }
            }

            HeroEvent.LoadHeroes -> {
                _uiState.update { it.copy(heroes = getMockHeroes()) }
                // else ->  Unit
            }
        }
    }
    companion object {
        private fun getMockHeroes(): List<Hero> = listOf(
            Hero(
                id = 1,
                name = "Deadpool",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s",
                description = "Hi, I'm Deadpool"
            ),
            Hero(
                id = 2,
                name = "Iron Man",
                imageUrl = "https://www.specfictionshop.com/cdn/shop/products/315455127_2253071438203857_6311282012262232749_n_2000x.jpg?v=1669836598",
                description = "Hi, I'm Iron Man"
            ),
            Hero(
                id = 3,
                name = "Harley Quinn",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOTzkRwd_h8AYu__LcSNij7TKsgCjDzQ-a4A&s",
                description = "Hi, I'm Harley Quinn"
            )
        )
    }
}