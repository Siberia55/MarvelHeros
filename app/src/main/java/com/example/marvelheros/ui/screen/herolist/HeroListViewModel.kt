package com.example.marvelheros.ui.screen.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.domain.usecase.GetHeroesUseCase
import com.example.marvelheros.ui.screen.herolist.state.HeroListState
import com.example.marvelheros.utils.MyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HeroListState())
    val state: StateFlow<HeroListState> = _state.asStateFlow()
    fun loadHeroes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getHeroesUseCase()) {
                is MyResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            heroes = result.data,
                            error = null
                        )
                    }
                }

                is MyResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                            errorCode = result.errorCode
                        )
                    }
                }
            }
        }
    }
}