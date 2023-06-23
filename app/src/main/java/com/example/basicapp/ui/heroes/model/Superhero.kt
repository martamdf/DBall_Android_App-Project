package com.example.basicapp.ui.heroes.model

data class Superhero(
    val id: String,
    val name: String,
    val photo: String,
    var favorite: Boolean,
    //var locations: List<SuperheroLocations>?,
)