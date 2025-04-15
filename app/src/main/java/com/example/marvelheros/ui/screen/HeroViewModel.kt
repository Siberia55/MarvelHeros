
package com.example.marvelheros.ui.screen

import android.R.attr.onClick
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.domain.model.Hero
import com.example.marvelheros.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.marvelheros.utils.MyResult
import androidx.compose.runtime.getValue
import com.example.marvelheros.domain.usecase.GetHeroesUseCase

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase//repository: HeroRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HeroUiState())
    val uiState: StateFlow<HeroUiState> = _uiState

    init {
        loadHeroes()
    }

    fun loadHeroes() {
        viewModelScope.launch {
          _uiState.update { it.copy( isLoading = true) }  //_uiState.value = _uiState.value.copy(isLoading = true)//, errorMessage = null)

            when (val result = getHeroesUseCase()/*repository.getHeroes()*/) {
                is MyResult.Success -> {
                    _uiState.update { it.copy(isLoading = false,
                        heroes = result.data,
                        errorMessage = null) }/*
                    _uiState.value = HeroUiState(
                        //isLoading = false,
                        heroes = result.data
                    )*/
                }

                is MyResult.Error -> { _uiState.update {
                    it.copy( isLoading = false,
                        errorMessage = result.message)
                }/*
                    _uiState.value = HeroUiState(
                        // isLoading = false, //heroes = emptyList(),
                        errorMessage = result.message
                    )*/
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