package com.example.basicapp.Data

import com.example.basicapp.Data.remote.GetHeroesResponse
import com.example.basicapp.Data.remote.LoginData
import com.example.basicapp.Data.remote.RemoteDataSource

class LoginRepository {
    private val remoteDataSource = LoginData()

    suspend fun getToken(email: String, pass: String): String {
        return remoteDataSource.getToken(email, pass)
    }
}