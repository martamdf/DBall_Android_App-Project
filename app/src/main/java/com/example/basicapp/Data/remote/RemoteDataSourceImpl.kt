package com.example.basicapp.Data.remote

import com.example.basicapp.Data.remote.request.GetHeroesRequestBody
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun getHeroes(): List<GetHeroesResponse> {
        return api.getHeroes(GetHeroesRequestBody())
    }
}