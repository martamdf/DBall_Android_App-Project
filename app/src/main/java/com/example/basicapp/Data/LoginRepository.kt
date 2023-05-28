package com.example.basicapp.Data

interface LoginRepository {
    // TODO: Check if this could be included in General Repository Logic...
    suspend fun getToken(email: String, pass: String): String
}