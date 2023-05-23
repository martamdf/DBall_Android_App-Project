package com.example.basicapp.Data.remote

data class GetHeroesResponse(
    val id: String,
    val name: String,
    val description: String,
    val photo: String,
)
