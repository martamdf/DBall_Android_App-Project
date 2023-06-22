package com.example.basicapp.data.remote

import com.example.basicapp.data.remote.request.GetHeroesRequestBody
import com.example.basicapp.data.remote.request.GetLocationsRequestBody
import com.example.basicapp.data.remote.request.SetFavoriteRequestBody
import com.example.basicapp.data.remote.response.GetHeroesResponse
import okhttp3.Credentials
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    private lateinit var token: String
    override suspend fun getHeroes(): List<GetHeroesResponse> {
        return api.getHeroes("Bearer $token", GetHeroesRequestBody())
    }

    override suspend fun getLocations(heroID: String): List<GetHeroLocationsResponse> {
        return api.getLocations("Bearer $token",GetLocationsRequestBody(heroID))
    }

    override suspend fun setFav(heroID: String){
        return api.setFav("Bearer $token",SetFavoriteRequestBody(heroID))
    }

    override suspend fun login(user: String, password: String): Result<String> {
        val token = runCatching {api.login(Credentials.basic(user, password, Charset.defaultCharset()))}
        if(token.isSuccess){
            this.token = token.toString()
        }
        return token
    }
}