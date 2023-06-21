package com.example.basicapp.data

import com.example.basicapp.ui.heroes.model.Superhero

interface Repository {
    suspend fun getHeroes(): List<Superhero>
    suspend fun getHero(heroID: String): Superhero
    suspend fun setFav(hero: Superhero): Superhero
    suspend fun login(user: String, password: String): String
}