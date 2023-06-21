package com.example.basicapp.data.remote

import com.example.basicapp.data.remote.request.GetHeroesRequestBody
import com.example.basicapp.data.remote.request.GetLocationsRequestBody
import com.example.basicapp.data.remote.request.SetFavoriteRequestBody
import com.example.basicapp.data.remote.response.GetHeroesResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

//const val TOKEN =
//    "eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjVCQTQwNTdBLTA4QjItNDZGMy05NDVBLUMyMDFFRThGRDlEOCIsImVtYWlsIjoibWFydGEubWFxdWVkYW5vQGdtYWlsLmVzIiwiZXhwaXJhdGlvbiI6NjQwOTIyMTEyMDB9.yH034GHpaztqsKK3bz7KrbXr2jQ3kO63Kk0RikgB-gE"

// TODO: Find the way to access TOKEN from SharedPrefs...
//const val TOKEN: String = Constants.instance().fetchValueString()

interface DragonBallApi {
    @POST("api/auth/login")
    suspend fun login(@Header("Authorization") token: String): String

    @POST("api/heros/all")
    //@Headers("Authorization: Bearer $TOKEN")
    suspend fun getHeroes(@Header("Authorization") token: String, @Body getHeroesRequestBody: GetHeroesRequestBody): List<GetHeroesResponse>

    @POST("api/heros/locations")
    //@Headers("Authorization: Bearer $TOKEN")
    suspend fun getLocations(@Header("Authorization") token: String, @Body getLocationsRequestBody: GetLocationsRequestBody): List<GetHeroLocationsResponse>

    @POST("api/data/herolike")
    //@Headers("Authorization: Bearer $TOKEN")
    suspend fun setFav(@Header("Authorization") token: String,@Body setFavoriteRequestBody: SetFavoriteRequestBody)

    //@POST("api/auth/login")
    //suspend fun getToken(@Header("Authorization") credentials: String): String
}



