package com.example.basicapp.Data

import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.Data.mappers.LocalToPresentationMapper
import com.example.basicapp.Data.mappers.RemoteToLocalMapper
import com.example.basicapp.Data.remote.RemoteDataSource
import com.example.basicapp.UI.model.Superhero
import com.example.basicapp.UI.model.SuperheroLocations

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
): Repository{


    override suspend fun getHeroes(): List<Superhero> {
        if (localDataSource.getHeroes().isEmpty()) {
            val remoteSuperheros = remoteDataSource.getHeroes()
            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperheros))
        }
        return localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHeroes())
    }

    override suspend fun getHero(heroID: String): Superhero {

        val remoteLocations  = remoteDataSource.getLocations(heroID)
        val superheroLocationsLocal = remoteToLocalMapper.mapGetHeroLocationResponse(remoteLocations)
        val superheroLocationsUI = localToPresentationMapper.mapLocalSuperheroLocations(superheroLocationsLocal)

        val superhero = localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHero(heroID))
        superhero.locations = superheroLocationsUI
        return superhero
    }
/*
    override suspend fun getLocations(heroID: String): List<SuperheroLocations> {
        return localToPresentationMapper.mapLocalSuperheroLocations(localDataSource.getLocations(heroID))
    }*/
}