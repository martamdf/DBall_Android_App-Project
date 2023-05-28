package com.example.basicapp.Data.remote

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.Exception
import java.nio.charset.Charset

class LoginData {
    // TODO: evaluate to include this funcionality as a part of RemoteDataSource Logic
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply{
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education/")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val api: DragonBallApi = retrofit.create(DragonBallApi::class.java)

    suspend fun getToken(email: String, pass: String): String {
        val credentials = Credentials.basic(email, pass, Charset.defaultCharset())
        // TODO: Need to manage the api login response, because is a string on receiving token
        //  or a dict when an error occurs
        try {
            return api.getToken(credentials)
        }catch (exc : Exception){
            return "Error"
        }
    }
}