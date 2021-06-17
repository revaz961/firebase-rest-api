package com.example.apiauthentication.model

data class PostUser(val email: String, val password: String, val returnSecureToken: Boolean = false)
