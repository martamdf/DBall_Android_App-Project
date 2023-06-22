package com.example.basicapp.data.remote

import com.example.basicapp.data.remote.response.GetHeroesResponse

interface RemoteDataSource {
    suspend fun getHeroes(): List<GetHeroesResponse>

    suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse>

    suspend fun setFav(heroID: String)

    suspend fun login(user: String, password: String): Result<String>
}