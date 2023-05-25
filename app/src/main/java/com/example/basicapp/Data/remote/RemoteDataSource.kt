package com.example.basicapp.Data.remote


interface RemoteDataSource {
    suspend fun getHeroes(): List<GetHeroesResponse>
}