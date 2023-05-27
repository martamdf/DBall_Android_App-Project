package com.example.basicapp.Data

import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.Data.mappers.LocalToPresentationMapper
import com.example.basicapp.Data.mappers.PresentationToLocalMapper
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
    private val presentationToLocalMapper: PresentationToLocalMapper
): Repository{


    override suspend fun getHeroes(): List<Superhero> {
        localDataSource.deleteData()
        if (localDataSource.getHeroes().isEmpty()) {
            val remoteSuperheros = remoteDataSource.getHeroes()
            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperheros))
        }
        return localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHeroes())
    }

    override suspend fun getHero(heroID: String): Superhero {

        //val remoteLocations  = remoteDataSource.getLocations(heroID)
        //val superheroLocationsLocal = remoteToLocalMapper.mapGetHeroLocationResponse(remoteLocations)

        //localDataSource.insertLocations(superheroLocationsLocal)
        if (localDataSource.getLocations(heroID).isEmpty()){
            val remoteLocations  = remoteDataSource.getLocations(heroID)
            localDataSource.insertLocations(remoteToLocalMapper.mapGetHeroLocationResponse(remoteLocations))
        }

        //val superheroLocationsUI = localToPresentationMapper.mapLocalSuperheroLocations(localDataSource.getLocations(heroID))
        val superheroLocations = remoteToLocalMapper.mapGetHeroLocationResponse(remoteDataSource.getLocations(heroID))
        val superheroLocationsUI = localToPresentationMapper.mapLocalSuperheroLocations(superheroLocations)

        val superhero = localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHero(heroID))
        superhero.locations = superheroLocationsUI
        return superhero
    }

    override suspend fun setFav(hero: Superhero): Superhero {
        hero.favorite = !hero.favorite
        localDataSource.insertHero(presentationToLocalMapper.mapPresentationSuperheroes(hero))
        remoteDataSource.setFav(hero.id)
        return hero
    }
    /*
        override suspend fun getLocations(heroID: String): List<SuperheroLocations> {
            return localToPresentationMapper.mapLocalSuperheroLocations(localDataSource.getLocations(heroID))
        }*/
}