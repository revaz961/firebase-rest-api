package com.example.apiauthentication.network

import com.example.apiauthentication.model.PostUser
import com.example.apiauthentication.model.SignIn
import com.example.apiauthentication.model.SignUp
import retrofit2.Response
import retrofit2.http.*

interface AuthentificationAPI {

    @POST("v1/accounts:signInWithPassword")
    suspend fun signIn(
        @Body user: PostUser,
        @Query("key") apiKey: String
    ): Response<SignIn>

    @POST("v1/accounts:signUp")
    suspend fun signUp(
        @Body user: PostUser,
        @Query("key") apiKey: String
    ): Response<SignUp>
}