package com.example.basicapp.UI.model

data class Superhero(
    val id: String,
    val name: String,
    val photo: String,
    val favorite: Boolean,
    var locations: List<SuperheroLocations>?,
)