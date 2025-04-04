package com.example.marvelheros.ui.screen

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

    //---------DS
    /*
    private val _uiState = MutableStateFlow(
        HeroUiState(
            heroes = getMockHeroes(),
            isLoading = false,
            errorMassage = "Error loading: ${e.localizedMessage}" // Передаём список героев
        )
    )
    val uiState: StateFlow<HeroUiState> get() = _uiState // Теперь это поток, а не просто объект

  init {
      loadHeroes()
  }
//------end
*/
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
    //-----------------DS
    /*  private fun loadHeroes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMassage = null) }
            try {
                val heroes = marvelReposytory.getCharacters()
                _uiState.update {
                    it.copy(
                        heroes = heroes,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMassage =  "Error loading: ${e.localizedMessage}",
                    isLoading = false )
                }
            }
        }
    }
    private fun selectHero (hero: Hero) {
        _uiState.update { it.copy(selectedHero = hero) }
    }
    private fun dismissHero () {
        _uiState.update { it.copy(selectedHero = null) }
    }
    private fun retry(){
        loadHeroes()
    }
   //---------------- end
*/

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