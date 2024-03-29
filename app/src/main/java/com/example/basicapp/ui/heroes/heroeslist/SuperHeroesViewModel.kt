package com.example.basicapp.ui.heroes.heroeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.data.Repository
import com.example.basicapp.ui.heroes.model.Superhero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _heroes = MutableLiveData<List<Superhero>>()
    val heroes: LiveData<List<Superhero>> get() = _heroes
    fun getHeroes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroes()
            }
            _heroes.value = result
        }
    }
}
