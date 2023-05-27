package com.example.basicapp.Data.remote

import com.example.basicapp.Data.remote.request.GetHeroesRequestBody
import com.example.basicapp.Data.remote.request.GetLocationsRequestBody

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {
    override suspend fun getHeroes(): List<GetHeroesResponse> {
        return api.getHeroes(GetHeroesRequestBody())
    }

    override suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse> {
        return api.getLocations(GetLocationsRequestBody(heroID))
    }
}