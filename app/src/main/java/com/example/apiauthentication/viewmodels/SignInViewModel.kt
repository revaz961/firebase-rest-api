package com.example.apiauthentication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiauthentication.App
import com.example.apiauthentication.model.PostUser
import com.example.apiauthentication.model.SignIn
import com.example.apiauthentication.model.SignUp
import com.example.apiauthentication.network.ResultHandler
import com.example.apiauthentication.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private val _signInLiveData = MutableLiveData<ResultHandler<SignIn>>()
    val signInLiveData: LiveData<ResultHandler<SignIn>> = _signInLiveData

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _signInLiveData.postValue(ResultHandler.Loading(true))
                post(email, password)
                _signInLiveData.postValue(ResultHandler.Loading(false))
            }
        }
    }

    private suspend fun post(email: String, password: String) {
        val response = RetrofitService.retrofitService.signIn(
            PostUser(email, password, true),
            App.instance.APIKey()
        )
        val body = response.body()
        if (response.isSuccessful) {
            _signInLiveData.postValue(ResultHandler.Success(body))
        } else {
            _signInLiveData.postValue(ResultHandler.Error(body, response.errorBody().toString()))
        }
    }

}