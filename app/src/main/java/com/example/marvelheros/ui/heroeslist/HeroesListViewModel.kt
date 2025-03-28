package com.example.marvelheros.ui.heroeslist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.data.repository.HeroRepository
import com.example.marvelheros.ui.heroeslist.HeroesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    private val _state = mutableStateOf(HeroesListState())
    val state: State<HeroesListState> = _state

    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = repository.getHeroes()) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        heroes = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Failure -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.exception.localizedMessage
                    )
                }
            }
        }
    }
}

sealed class HeroesListState {
    data class Data(
        val heroes: List<Hero> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ) : HeroesListState()
}