package com.example.basicapp.Data.remote

import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.nio.charset.Charset

class LoginData {

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
        return api.getToken(credentials)
    }
}