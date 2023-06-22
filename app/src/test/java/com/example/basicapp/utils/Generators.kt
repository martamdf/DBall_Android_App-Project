package com.example.basicapp.utils

import com.example.basicapp.data.local.model.LocalSuperhero
import com.example.basicapp.data.remote.GetHeroIdLocation
import com.example.basicapp.data.remote.GetHeroLocationsResponse
import com.example.basicapp.data.remote.response.GetHeroesResponse
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations

fun generateHeroes(size: Int): List<Superhero> {
    val locations : List<SuperheroLocations> = emptyList()
    return (0 until size).map {
        Superhero(
            "ID $it",
            "Name $it",
            "photo",
            false,
            locations) }
}

fun generateGetHeroesResponse(size: Int): List<GetHeroesResponse> {
    return (0 until size).map {
        GetHeroesResponse(
            "ID $it",
            "Name $it",
            "Description $it",
            "Photo $it",
            false) }
}

fun generateLocalSuperhero(size: Int): List<LocalSuperhero> {
    return (0 until size).map {
        LocalSuperhero(
            "ID $it",
        "Name $it",
        "photo",
        false) }
}
fun generateOneLocalSuperhero(): LocalSuperhero {
    return LocalSuperhero("ID", "Name", "photo", false)
}

fun generateOnePresentationSuperhero(): Superhero {
    val locations : List<SuperheroLocations> = emptyList()
    return Superhero(
        "Paco Perez",
        "Name",
        "photo",
        false, locations)
}

fun generateGetHeroesLocationsResponse(size: Int): List<GetHeroLocationsResponse> {
    val heroID = GetHeroIdLocation("idf")
    return (0 until size).map {
        GetHeroLocationsResponse(
            "id $it",
        "longitud $it",
        "latitud $it",
        heroID,
        "")}
}
fun generateOneSuperhero(): Superhero {
    return Superhero("id",
        "Name",
        "photo",
        false,
        emptyList())
}

fun generateSetFavResponse() {
    return
}
