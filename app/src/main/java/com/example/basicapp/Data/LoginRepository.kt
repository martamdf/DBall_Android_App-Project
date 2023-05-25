package com.example.basicapp.Data

import com.example.basicapp.Data.remote.LoginData
class LoginRepository {
    private val remoteDataSource = LoginData()

    suspend fun getToken(email: String, pass: String): String {
        return remoteDataSource.getToken(email, pass)
    }
}