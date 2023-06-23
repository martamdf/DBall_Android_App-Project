package com.example.basicapp.ui.heroes.herodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.data.Repository
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _hero = MutableLiveData<Superhero>()
    val hero: LiveData<Superhero> get() = _hero

    private val _locations = MutableLiveData<List<SuperheroLocations>>()
    val locations: LiveData<List<SuperheroLocations>> get() = _locations

    fun getHero(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHero(heroID)
            }
            _hero.value = result
        }
    }
    fun getLocations(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getLocations(heroID)
            }
            _locations.value = result
        }
    }
    fun setFav(hero: Superhero) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.setFav(hero)
            }
            _hero.value = result
        }
    }
}
