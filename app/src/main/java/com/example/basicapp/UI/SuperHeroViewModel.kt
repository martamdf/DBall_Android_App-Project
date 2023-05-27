package com.example.basicapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.Data.Repository
import com.example.basicapp.UI.model.Superhero
import com.example.basicapp.UI.model.SuperheroLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _hero = MutableLiveData<Superhero>()
    val hero: LiveData<Superhero> get() = _hero

    fun getHero(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHero(heroID)
            }
            _hero.value = result
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
