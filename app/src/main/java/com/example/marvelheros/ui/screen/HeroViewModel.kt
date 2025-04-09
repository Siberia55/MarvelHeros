/*package com.example.marvelheros.ui.screen

import android.util.Log
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
        HeroUiState(heroes = getMockHeroes())
    )
    val uiState: StateFlow<HeroUiState> get() = _uiState

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

            }
            // else ->  Unit
            //HeroEvent.Retry -> retry() //---- DS
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

    //}
    init {
        Log.d("HeroDebug", "Loaded heroes: ${_uiState.value.heroes}")
    }
}

 */

package com.example.marvelheros.ui.screen

import android.R.attr.onClick
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.data.model.Hero
import com.example.marvelheros.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.marvelheros.utils.MyResult
import androidx.compose.runtime.getValue


/*
@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HeroUiState())
    val uiState: StateFlow<HeroUiState> = _uiState

    init {
        loadHeroes()
    }

    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.HeroSelected -> selectHero(event.hero)
            HeroEvent.DismissHero -> dismissHero()
            HeroEvent.LoadHeroes -> loadHeroes()
        }
    }

    private fun loadHeroes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val heroes = repository.getHeroes()
                _uiState.update {
                    it.copy(
                        heroes = heroes,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    private fun selectHero(hero: Hero) {
        _uiState.update { it.copy(selectedHero = hero) }
    }

    private fun dismissHero() {
        _uiState.update { it.copy(selectedHero = null) }
    }
}

 */

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HeroUiState())
    val uiState: StateFlow<HeroUiState> = _uiState

    init {
        loadHeroes()
    }

    fun loadHeroes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)//, errorMessage = null)

            when (val result = repository.getHeroes()) {
                is MyResult.Success -> {
                    _uiState.value = HeroUiState(
                        //isLoading = false,
                        heroes = result.data
                    )
                }

                is MyResult.Error -> {
                    _uiState.value = HeroUiState(
                        // isLoading = false, //heroes = emptyList(),
                        errorMessage = result.message
                    )
                }
            }
        }
    }


    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.HeroSelected -> {
                _uiState.value = _uiState.value.copy(selectedHero = event.hero)
            }

            HeroEvent.DismissHero -> {
                _uiState.value = _uiState.value.copy(selectedHero = null)
            }

            else -> {}
        }
    }
}



@Composable
fun MainScreen(viewModel: HeroViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.errorMessage != null -> {
            Text("Ошибка: ${state.errorMessage}")
        }
        else -> {
            MainContent(heroes = state.heroes,
            onHeroClick =  {} )
        }
    }
}