package com.example.basicapp.data.remote.fakes

import com.example.basicapp.Data.remote.GetHeroLocationsResponse
import com.example.basicapp.Data.remote.response.GetHeroesResponse
import com.example.basicapp.Data.remote.RemoteDataSource
import com.example.basicapp.utils.generateGetHeroesLocationsResponse
import com.example.basicapp.utils.generateGetHeroesResponse
import com.example.basicapp.utils.generateSetFavResponse

class FakeRemoteDataSource: RemoteDataSource {

    override suspend fun getHeroes(): List<GetHeroesResponse> {
        return generateGetHeroesResponse(15)
    }

    override suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse> {
        return generateGetHeroesLocationsResponse(3)
    }

    override suspend fun setFav(heroID: String) {
        return generateSetFavResponse()
    }
}