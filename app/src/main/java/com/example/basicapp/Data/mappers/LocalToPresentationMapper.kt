package com.example.basicapp.Data.mappers

import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.UI.model.Superhero

import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {
    fun mapLocalSuperheroes(localSuperheroes: List<LocalSuperhero>): List<Superhero> {
        return localSuperheroes.map { mapLocalSuperheroes(it) }
    }

    fun mapLocalSuperheroes(getHeroesResponse: LocalSuperhero): Superhero {
        return Superhero(getHeroesResponse.id, getHeroesResponse.name, getHeroesResponse.photo, getHeroesResponse.favorite)
    }
}