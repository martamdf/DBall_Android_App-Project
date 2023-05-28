package com.example.basicapp.Data

import com.example.basicapp.Data.remote.LoginData

class LoginRepositoryImpl: LoginRepository {
    private val remoteDataSource = LoginData()

    override suspend fun getToken(email: String, pass: String): String {
        return remoteDataSource.getToken(email, pass)
    }
}