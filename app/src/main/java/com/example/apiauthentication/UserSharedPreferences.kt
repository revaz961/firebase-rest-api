package com.example.apiauthentication

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object UserSharedPreferences {
    private val sharedPreferences: SharedPreferences by lazy {
        App.instance.getSharedPreferences("local_storage", MODE_PRIVATE)
    }

    fun getToken(): Pair<String, String>? =
        if (sharedPreferences.contains("idToken") && sharedPreferences.contains("refreshToken"))
            Pair(
                sharedPreferences.getString("idToken", "unknown")!!,
                sharedPreferences.getString("refreshToken", "unknown")!!
            )
        else
            null


    fun setToken(idToken: String, refreshToken: String) {
        val edit = sharedPreferences.edit()
        edit.putString("idToken", idToken)
        edit.putString("refreshToken", refreshToken)
        edit.apply()
    }

    fun clear() = sharedPreferences.edit().clear().apply()
}