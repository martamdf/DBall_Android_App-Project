package com.example.basicapp.Data.remote

import com.example.basicapp.Data.remote.response.GetHeroesResponse


interface RemoteDataSource {
    suspend fun getHeroes(): List<GetHeroesResponse>

    suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse>

    suspend fun setFav(heroID: String)

    suspend fun login(user: String, password: String): String
}