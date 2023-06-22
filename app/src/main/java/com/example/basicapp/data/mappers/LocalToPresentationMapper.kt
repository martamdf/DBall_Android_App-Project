package com.example.basicapp.data.mappers

import com.example.basicapp.data.local.model.LocalSuperHeroLocation
import com.example.basicapp.data.local.model.LocalSuperhero
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations

import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {
    fun mapLocalSuperheroes(localSuperheroes: List<LocalSuperhero>): List<Superhero> {
        return localSuperheroes.map { mapLocalSuperheroes(it) }
    }
    fun mapLocalSuperheroes(getHeroesResponse: LocalSuperhero): Superhero {
        val superHeroLocations:List<SuperheroLocations> = emptyList()
        return Superhero(getHeroesResponse.id, getHeroesResponse.name, getHeroesResponse.photo, getHeroesResponse.favorite, superHeroLocations)
    }
    fun mapLocalSuperheroLocations(getHeroLocationsResponse: List<LocalSuperHeroLocation>): List<SuperheroLocations> {
        return getHeroLocationsResponse.map { mapLocalSuperheroLocations(it) }
    }

    private fun mapLocalSuperheroLocations(getHeroesLocationsResponse: LocalSuperHeroLocation): SuperheroLocations {
        return SuperheroLocations(getHeroesLocationsResponse.id, getHeroesLocationsResponse.latitude, getHeroesLocationsResponse.longitude, getHeroesLocationsResponse.dateShow)
    }
}