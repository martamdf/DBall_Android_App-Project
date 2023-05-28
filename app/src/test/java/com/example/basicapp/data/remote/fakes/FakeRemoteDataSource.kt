package com.example.basicapp.data.remote.fakes

import com.example.basicapp.Data.remote.GetHeroLocationsResponse
import com.example.basicapp.Data.remote.GetHeroesResponse
import com.example.basicapp.Data.remote.RemoteDataSource
import com.example.basicapp.utils.generateGetHeroesResponse

class FakeRemoteDataSource: RemoteDataSource {

    override suspend fun getHeroes(): List<GetHeroesResponse> {
        return generateGetHeroesResponse(15)
    }

    override suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun setFav(heroID: String) {
        TODO("Not yet implemented")
    }
}