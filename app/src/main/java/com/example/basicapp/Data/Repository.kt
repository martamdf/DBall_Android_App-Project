package com.example.basicapp.Data

import com.keepcoding.androidavanzado.ui.model.Superhero

interface Repository {
    suspend fun getHeroes(): List<Superhero>
}