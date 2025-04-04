package com.example.marvelheros.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.data.model.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class HeroViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        HeroUiState(
            heroes = getMockHeroes() // Загружаем список героев (можно потом через use case)
        )
    )
    val uiState: HeroUiState get() = _uiState.value

    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.HeroSelected -> {
                _uiState.update { it.copy(selectedHero = event.hero) }
            }
        }
    }

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

/*
@HiltViewModel
class HeroViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HeroState())
    val state: StateFlow<HeroState> = _state

    private val heroes = listOf(
        Hero(1, "Deadpool", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvakm_zio2J6a-PadL8SE6DjgZOB_5FlJz3w&s", "Hi, I'm Deadpool"),
        Hero(2, "Iron Man", "https://www.specfictionshop.com/cdn/shop/products/315455127_2253071438203857_6311282012262232749_n_2000x.jpg?v=1669836598", "Hi, I'm Iron Man"),
        Hero(3, "Harley Quinn", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOTzkRwd_h8AYu__LcSNij7TKsgCjDzQ-a4A&s", "Hi, I'm Harley Quinn")
    )

    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.LoadHeroes -> {
                _state.value = _state.value.copy(heroes = heroes)
            }
            is HeroEvent.SelectHero -> {
                _state.value = _state.value.copy(selectedHero = event.hero)
            }
            is HeroEvent.DismissHero -> {
                _state.value = _state.value.copy(selectedHero = null)
            }
        }
    }
}

 */