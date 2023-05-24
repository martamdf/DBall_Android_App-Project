package com.example.basicapp.Data.local

import com.example.basicapp.Data.local.model.LocalSuperhero

interface LocalDataSource {
    suspend fun getHeroes(): List<LocalSuperhero>

    //suspend fun insertHero(localSuperhero: LocalSuperhero)

    suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>)
}