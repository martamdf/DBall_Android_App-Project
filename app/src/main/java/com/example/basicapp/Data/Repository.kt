package com.example.basicapp.Data

import com.example.basicapp.UI.heroes.model.Superhero

interface Repository {
    suspend fun getHeroes(): List<Superhero>
    suspend fun getHero(heroID: String): Superhero
    suspend fun setFav(hero: Superhero): Superhero
}