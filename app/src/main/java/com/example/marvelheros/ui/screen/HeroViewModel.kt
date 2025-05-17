package com.example.marvelheros.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.R
import com.example.marvelheros.domain.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.marvelheros.utils.MyResult
import com.example.marvelheros.domain.usecase.GetHeroesUseCase
import com.example.marvelheros.utils.ErrorCode

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase,
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HeroUiState())
    val uiState: StateFlow<HeroUiState> = _uiState

    init {
        loadHeroes()
    }

    fun loadHeroes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getHeroesUseCase()) {
                is MyResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            heroes = result.data,
                            errorMessage = null
                        )
                    }
                }

                is MyResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = when (result.errorCode) {
                                ErrorCode.NETWORK_ERROR -> R.string.no_network_error
                                ErrorCode.SERVER_ERROR -> R.string.server_error
                                ErrorCode.UNKNOWN_ERROR -> R.string.unknown_error
                            },
                            serverErrorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun loadHeroDetails(heroId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    heroDetailsLoading = true,
                    heroDetailsError = null
                )
            }

            when (val result = repository.getHeroById(heroId)) {
                is MyResult.Success -> {
                    _uiState.update {
                        it.copy(
                            selectedHero = result.data,
                            heroDetailsLoading = false
                        )
                    }
                }

                is MyResult.Error -> {
                    _uiState.update {
                        it.copy(
                            heroDetailsLoading = false,
                            heroDetailsError = when (result.errorCode) {
                                ErrorCode.NETWORK_ERROR -> R.string.no_network_error
                                ErrorCode.SERVER_ERROR -> R.string.server_error
                                else -> R.string.unknown_error
                            },
                            serverErrorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: HeroEvent) {
        when (event) {
            is HeroEvent.HeroSelected -> {
                _uiState.update { it.copy(selectedHero = event.hero) }
                loadHeroDetails(event.hero.id)
            }

            HeroEvent.DismissHero -> {
                _uiState.update { it.copy(selectedHero = null) }
            }

            is HeroEvent.ShowError -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = event.message
                    )
                }
            }

            else -> {}
        }
    }
}

