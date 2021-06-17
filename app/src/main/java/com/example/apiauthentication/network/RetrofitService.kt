package com.example.apiauthentication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://identitytoolkit.googleapis.com/"
    val retrofitService: AuthentificationAPI by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthentificationAPI::class.java)
    }
}