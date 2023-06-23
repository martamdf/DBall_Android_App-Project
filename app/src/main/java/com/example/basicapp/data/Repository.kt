package com.example.basicapp.data

import com.example.basicapp.data.local.model.LocalSuperHeroLocation
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations

interface Repository {
    suspend fun getHeroes(): List<Superhero>
    suspend fun getHero(heroID: String): Superhero
    suspend fun getLocations(heroID: String): List<SuperheroLocations>
    suspend fun setFav(hero: Superhero): Superhero
    suspend fun login(user: String, password: String): Result<String>
}