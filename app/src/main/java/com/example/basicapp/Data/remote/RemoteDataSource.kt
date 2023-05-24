package com.keepcoding.androidavanzado.data.remote

import com.example.basicapp.Data.remote.GetHeroesResponse

interface RemoteDataSource {
    suspend fun getHeroes(): List<GetHeroesResponse>
}