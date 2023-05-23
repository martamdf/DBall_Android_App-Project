package com.example.basicapp.Data

import com.example.basicapp.Data.remote.GetHeroesResponse
import com.example.basicapp.Data.remote.RemoteDataSource

class Repository {
    private val remoteDataSource = RemoteDataSource()

    suspend fun getHeroes(): List<GetHeroesResponse> {
        return remoteDataSource.getHeroes()
    }
}