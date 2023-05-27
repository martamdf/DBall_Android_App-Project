package com.example.basicapp.Data.mappers

import com.example.basicapp.Data.local.model.LocalSuperHeroLocation
import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.UI.model.Superhero
import com.example.basicapp.UI.model.SuperheroLocations

import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor() {

    fun mapPresentationSuperheroes(superHero: Superhero): LocalSuperhero {
        return LocalSuperhero(superHero.id, superHero.name, superHero.photo, superHero.favorite)
    }
}