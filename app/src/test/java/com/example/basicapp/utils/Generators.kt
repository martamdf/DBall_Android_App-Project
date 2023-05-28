package com.example.basicapp.utils

import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.Data.remote.GetHeroIdLocation
import com.example.basicapp.Data.remote.GetHeroLocationsResponse
import com.example.basicapp.Data.remote.response.GetHeroesResponse
import com.example.basicapp.UI.heroes.model.Superhero
import com.example.basicapp.UI.heroes.model.SuperheroLocations

fun generateHeroes(size: Int): List<Superhero> {
    val locations : List<SuperheroLocations> = emptyList()
    return (0 until size).map { Superhero("ID $it", "Name $it", "photo", false, locations) }
}


fun generateGetHeroesResponse(size: Int): List<GetHeroesResponse> {
    return (0 until size).map { GetHeroesResponse("ID $it", "Name $it", "Description $it", "Photo $it", false) }
}

fun generateLocalSuperhero(size: Int): List<LocalSuperhero> {
    return (0 until size).map { LocalSuperhero("ID $it", "Name $it", "photo", false) }
}
fun generateOneLocalSuperhero(): LocalSuperhero {
    return LocalSuperhero("ID", "Name", "photo", false)
}

fun generateGetHeroesLocationsResponse(size: Int): List<GetHeroLocationsResponse> {
    val heroID = GetHeroIdLocation("idf")
    return (0 until size).map { GetHeroLocationsResponse("id $it", "longitud $it", "latitud $it", heroID, "")}
}
fun generateOneSuperhero(): Superhero {
    return Superhero("id", "Name", "photo", false, emptyList())
}

fun generateSetFavResponse(): Int {
    return 201
}

//generateSetFavResponse