package com.example.basicapp.data.remote

import com.example.basicapp.data.remote.request.GetHeroesRequestBody
import com.example.basicapp.data.remote.request.GetLocationsRequestBody
import com.example.basicapp.data.remote.request.SetFavoriteRequestBody
import com.example.basicapp.data.remote.response.GetHeroesResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApi {
    @POST("api/auth/login")
    suspend fun login(@Header("Authorization") token: String): String

    @POST("api/heros/all")
    suspend fun getHeroes(@Header("Authorization") token: String, @Body getHeroesRequestBody: GetHeroesRequestBody): List<GetHeroesResponse>

    @POST("api/heros/locations")
    suspend fun getLocations(@Header("Authorization") token: String, @Body getLocationsRequestBody: GetLocationsRequestBody): List<GetHeroLocationsResponse>

    @POST("api/data/herolike")
    suspend fun setFav(@Header("Authorization") token: String,@Body setFavoriteRequestBody: SetFavoriteRequestBody)
}



