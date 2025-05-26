package com.example.marvelheros.ui.screen.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheros.domain.repository.HeroRepository
import com.example.marvelheros.ui.screen.heroState.HeroDetailState
import com.example.marvelheros.utils.MyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HeroDetailState())
    val state: StateFlow<HeroDetailState> = _state.asStateFlow()
    fun loadHero(heroId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.getHeroById(heroId)) {
                is MyResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            hero = result.data,
                            error = null
                        )
                    }
                }

                is MyResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}
