package com.example.basicapp.data.remote

class LoginData {
/*    // TODO: evaluate to include this funcionality as a part of RemoteDataSource Logic
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
        try{
            val token = api.getToken(Credentials.basic(email, pass))
            this.token = token

            return token
        } catch (exc : Exception){
            return "Error"
        }
    }*/
}