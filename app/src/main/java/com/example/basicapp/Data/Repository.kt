package com.example.basicapp.Data

import com.example.basicapp.UI.model.Superhero


interface Repository {
    suspend fun getHeroes(): List<Superhero>
}