package com.example.basicapp.data.local.fakes

import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.local.model.LocalSuperHeroLocation
import com.example.basicapp.Data.local.model.LocalSuperhero
import javax.inject.Inject

class FakeLocalDataSource @Inject constructor() : LocalDataSource {

    private var firstTime = true

    private var heroes = mutableListOf<LocalSuperhero>()

    override suspend fun getHeroes(): List<LocalSuperhero> {
        return if (firstTime){
            firstTime = false
            emptyList()
        }  else {
            heroes
        }
    }

    override suspend fun insertHero(localSuperhero: LocalSuperhero) {
        TODO("Not yet implemented")
    }

    override suspend fun getHero(heroID: String): LocalSuperhero {
        TODO("Not yet implemented")
    }

    override suspend fun getLocations(heroID: String): List<LocalSuperHeroLocation> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocations(localHeroLocations: List<LocalSuperHeroLocation>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteData() {
        TODO("Not yet implemented")
    }

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>) {
        heroes.addAll(localSuperheros)
    }
}