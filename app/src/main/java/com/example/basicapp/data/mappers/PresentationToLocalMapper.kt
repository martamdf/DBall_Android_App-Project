package com.example.basicapp.data.mappers

import com.example.basicapp.data.local.model.LocalSuperhero
import com.example.basicapp.ui.heroes.model.Superhero

import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor() {

    fun mapPresentationSuperheroes(superHero: Superhero): LocalSuperhero {
        return LocalSuperhero(superHero.id, superHero.name, superHero.photo, superHero.favorite)
    }
}