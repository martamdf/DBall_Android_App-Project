package com.example.basicapp.data

import com.example.basicapp.data.local.LocalDataSource
import com.example.basicapp.data.local.model.LocalSuperHeroLocation
import com.example.basicapp.data.mappers.LocalToPresentationMapper
import com.example.basicapp.data.mappers.PresentationToLocalMapper
import com.example.basicapp.data.mappers.RemoteToLocalMapper
import com.example.basicapp.data.remote.RemoteDataSource
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val presentationToLocalMapper: PresentationToLocalMapper
): Repository {
    override suspend fun getHeroes(): List<Superhero> {
        // TODO: Find the way to, after a while (i.e. one day), do the remote request again,
        //  because the remote data source could be modified.
        if (localDataSource.getHeroes().isEmpty()) {
            val remoteSuperheros = remoteDataSource.getHeroes()
            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperheros))
        }
        return localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHeroes())
    }
    override suspend fun getHero(heroID: String): Superhero {
        return localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHero(heroID))
    }

    override suspend fun getLocations(heroID: String): List<SuperheroLocations>{
        val superheroLocations = remoteToLocalMapper.mapGetHeroLocationResponse(remoteDataSource.getLocations(heroID))
        return localToPresentationMapper.mapLocalSuperheroLocations(superheroLocations)
    }

    override suspend fun setFav(hero: Superhero): Superhero {
        hero.favorite = !hero.favorite
        localDataSource.insertHero(presentationToLocalMapper.mapPresentationSuperheroes(hero))
        remoteDataSource.setFav(hero.id)
        return hero
    }
    override suspend fun login(user: String, password: String): Result<String> {
        return remoteDataSource.login(user, password)
    }
}