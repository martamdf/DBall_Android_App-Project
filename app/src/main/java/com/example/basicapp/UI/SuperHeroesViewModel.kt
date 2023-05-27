package com.example.basicapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.Data.Repository
import com.example.basicapp.UI.model.Superhero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperHeroesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _heroes = MutableLiveData<List<Superhero>>()
    val heroes: LiveData<List<Superhero>> get() = _heroes

/*
    private val _hero = MutableLiveData<Superhero>()
    val hero: LiveData<Superhero> get() = _hero*/

    fun getHeroes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroes()
            }
            _heroes.value = result
        }
    }
/*
    fun getHero(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHero(heroID)
            }
            _hero.value = result
        }
    }*/
}













    /////////// EJEMPLOS CORRUTINAS ///////////
/*
    fun ejecutaTareaCostosa() {
        Log.d("SuperheroViewModel", "Antes de la espera")
        Thread.sleep(10000)
        Log.d("SuperheroViewModel", "Después de la espera")
    }

    fun ejecutaTareaCostosaConCorrutina() { // Sigue funcionando despúes de cerrar la aplicación
        GlobalScope.launch {// No suele usarse
            Log.d("SuperheroViewModel", "Antes de la espera")
            Thread.sleep(10000)
            Log.d("SuperheroViewModel", "Después de la espera")
        }
    }

    fun ejecutaTareaCostosaConCorrutinaEnViewModelScope() {
        viewModelScope.launch {
            Log.d("SuperheroViewModel", "Antes de la espera")
            Thread.sleep(10000)
            Log.d("SuperheroViewModel", "Después de la espera")
        }
    }

    fun ejecutaTareaCostosaConCorrutinaEnViewModelScopeYDispatcher() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SuperheroViewModel", "Antes de la espera")
            Thread.sleep(10000)
            Log.d("SuperheroViewModel", "Después de la espera")
        }
    }

    fun ejecutaTareaCostosaConCorrutinaEnViewModelScopeYLaunch() {
        viewModelScope.launch {
            val myJob = launch {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(10000)
                Log.d("SuperheroViewModel", "Después de la espera") // T1 = 10
            }
            Log.d("SuperheroViewModel", "Result ${myJob.isActive}") // T2 = 0
        }
    }

    fun ejecutaTareaCostosaConCorrutinaEnViewModelScopeYWithContext() {
        viewModelScope.launch {
            Log.d("SuperheroViewModel", "Antes de la corrutina") // T = 0

            val result = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(10000)
                Log.d("SuperheroViewModel", "Después de la espera") // T1 = 10
                42.0
            }

            Log.d("SuperheroViewModel", "Result $result") // T2 = 10
        }
    }

    fun ejecutaTareaCostosaConCorrutinaAsync() {
        viewModelScope.launch {
            Log.d("SuperheroViewModel", "Antes de la corrutina") // T = 0

            val result = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(10000)
                Log.d("SuperheroViewModel", "Después de la espera") // T1 = 10
                42.0
            }
            Log.d("SuperheroViewModel", "Antes de result") // T3 = 0

            Log.d("SuperheroViewModel", "Result ${result.await()}") // T2 = 10

            Log.d("SuperheroViewModel", "Despues de result") // T4 = 10
        }
    }


    fun ejemplo1AsyncWithContext() {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(10000)
                Log.d("SuperheroViewModel", "Después de la espera") // T1 = 10
                42.0
            }

            Log.d("SuperheroViewModel", "Result ${result}") // T2 = 10

        }
    }

    fun ejemplo2AsyncWithContext() {
        viewModelScope.launch {

            Log.d("SuperheroViewModel", "Antes de la espera") // T = 0

            val result1 = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(1000)
                Log.d("SuperheroViewModel", "Después de la espera") // T = 1
                1
            }

            Log.d("SuperheroViewModel", "Result $result1") // T = 1

            val result2 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result2") // T = 1
                Thread.sleep(2000)
                Log.d("SuperheroViewModel", "Después de la espera result2") // T = 3
                2
            }

            val result3 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result3") // T = 1
                Thread.sleep(3000)
                Log.d("SuperheroViewModel", "Después de la espera result3") // T = 4
                3
            }

            Log.d("SuperheroViewModel", "Result 2: ${result2.await()} Result 3: ${result3.await()}") // T = 4

        }
    }

    fun ejemplo3AsyncWithContext() {
        viewModelScope.launch {

            Log.d("SuperheroViewModel", "Antes de la espera") // T = 0

            val result1 = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(1000)
                Log.d("SuperheroViewModel", "Después de la espera") // T = 1
                1
            }

            Log.d("SuperheroViewModel", "Result $result1") // T = 1

            val result2 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result2") // T = 1
                Thread.sleep(2000)
                Log.d("SuperheroViewModel", "Después de la espera result2") // T = 3
                2
            }

            val result3 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result3") // T = 1
                Thread.sleep(3000)
                Log.d("SuperheroViewModel", "Después de la espera result3") // T = 4
                3
            }

            Log.d("SuperheroViewModel", "Result 2: ${result2.await()}") // T = 3
            Log.d("SuperheroViewModel", "Result 2: ${result2.await()} Result 3: ${result3.await()}") // T = 4
        }
    }

    fun ejemplo4AsyncWithContext() {
        viewModelScope.launch {

            Log.d("SuperheroViewModel", "Antes de la espera") // T = 0

            val result1 = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(1000)
                Log.d("SuperheroViewModel", "Después de la espera") // T = 1
                1
            }

            Log.d("SuperheroViewModel", "Result $result1") // T = 1

            val result2 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result2") // T = 1
                Thread.sleep(2000)
                Log.d("SuperheroViewModel", "Después de la espera result2") // T = 3
                2
            }

            val result3 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result3") // T = 1
                Thread.sleep(3000)
                Log.d("SuperheroViewModel", "Después de la espera result3") // T = 4
                3
            }

            Log.d("SuperheroViewModel", "Result 2: ${result3.await()}") // T = 4
            Log.d("SuperheroViewModel", "Result 2: ${result2.await()} Result 3: ${result3.await()}") // T = 4
        }
    }

    fun ejemplo5AsyncWithContext() {
        viewModelScope.launch {

            Log.d("SuperheroViewModel", "Antes de la espera") // T = 0

            val result1 = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(1000)
                Log.d("SuperheroViewModel", "Después de la espera") // T = 1
                1
            }

            Log.d("SuperheroViewModel", "Result $result1") // T = 1

            val result2 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result2") // T = 1
                Thread.sleep(2000)
                Log.d("SuperheroViewModel", "Después de la espera result2") // T = 3
                2
            }

            val result3 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result3") // T = 1
                Thread.sleep(3000)
                Log.d("SuperheroViewModel", "Después de la espera result3") // T = 4
                3
            }

            Log.d("SuperheroViewModel", "Result 2: ${result3.await()}") // T = 4
            Log.d("SuperheroViewModel", "Result 2: ${result2.await()}") // T = 4
        }
    }

    fun ejemplo6AsyncWithContext() {
        viewModelScope.launch {

            Log.d("SuperheroViewModel", "Antes de la espera") // T = 0

            val result1 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera") // T = 0
                Thread.sleep(5000)
                Log.d("SuperheroViewModel", "Después de la espera") // T = 5
                1
            }

            Log.d("SuperheroViewModel", "Result $result1") // T = 0

            val result2 = async(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result2") // T = 0
                Thread.sleep(2000)
                Log.d("SuperheroViewModel", "Después de la espera result2") // T = 2
                val a = 2 + result1.await()
                Log.d("SuperheroViewModel", "Después de la espera2 $a") // T = 5
                a
            }

            val result3 = withContext(Dispatchers.IO) {
                Log.d("SuperheroViewModel", "Antes de la espera result3") // T = 0
                Thread.sleep(3000)
                Log.d("SuperheroViewModel", "Después de la espera result3") // T = 3
                3
            }

            Log.d("SuperheroViewModel", "Result 3: $result3") // T = 3
            Log.d("SuperheroViewModel", "Result 2: ${result2.await()}") // T = 5
        }
    }*/
