package com.example.basicapp.Data.local

import com.example.basicapp.Data.local.model.LocalSuperHeroLocation
import com.example.basicapp.Data.local.model.LocalSuperhero
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SuperheroDAO): LocalDataSource {
    override suspend fun getHeroes(): List<LocalSuperhero>{
        return dao.getAll()
    }

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>){
        dao.insertAllList(localSuperheros)
    }

    override suspend fun getHero(heroID: String): LocalSuperhero {
        return dao.getHero(heroID)
    }

    override suspend fun getLocations(heroID: String): List<LocalSuperHeroLocation> {
        return dao.getLocation(heroID)
    }
    override suspend fun insertLocations(localHeroLocations: List<LocalSuperHeroLocation>) {
        dao.insertLocationsList(localHeroLocations)
    }
}
