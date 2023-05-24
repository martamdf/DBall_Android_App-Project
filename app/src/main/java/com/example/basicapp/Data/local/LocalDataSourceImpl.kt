package com.example.basicapp.Data.local

import com.example.basicapp.Data.local.model.LocalSuperhero
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SuperheroDAO): LocalDataSource {
    override suspend fun getHeroes(): List<LocalSuperhero>{
        return dao.getAll()
    }

/*
    override suspend fun insertHero(localSuperhero: LocalSuperhero){
        dao.insertAllList(listOf(localSuperhero))
    }*/

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>){
        dao.insertAllList(localSuperheros)
    }

}
