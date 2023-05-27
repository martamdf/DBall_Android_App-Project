package com.example.basicapp.Data

import com.example.basicapp.UI.model.Superhero
import com.example.basicapp.UI.model.SuperheroLocations

interface Repository {
    suspend fun getHeroes(): List<Superhero>

    suspend fun getHero(heroID: String): Superhero
    suspend fun setFav(hero: Superhero): Superhero

    //suspend fun getLocations(heroID: String): List<SuperheroLocations>
}