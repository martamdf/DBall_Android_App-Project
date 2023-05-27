package com.example.basicapp.Data.remote


interface RemoteDataSource {
    suspend fun getHeroes(): List<GetHeroesResponse>

    suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse>

    suspend fun setFav(heroID: String)
}