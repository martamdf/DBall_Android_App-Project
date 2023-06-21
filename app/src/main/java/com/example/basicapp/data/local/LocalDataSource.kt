package com.example.basicapp.data.local

import com.example.basicapp.data.local.model.LocalSuperhero

interface LocalDataSource {
    suspend fun getHeroes(): List<LocalSuperhero>
    suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>)
    suspend fun insertHero(hero: LocalSuperhero)
    suspend fun getHero(heroID: String): LocalSuperhero
    //suspend fun getLocations(heroID: String): List<LocalSuperHeroLocation>
    //suspend fun insertLocations(localHeroLocations: List<LocalSuperHeroLocation>)
    suspend fun deleteData()
}