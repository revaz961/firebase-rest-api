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

class SignUpViewModel : ViewModel() {

    private val _signUpLiveData = MutableLiveData<ResultHandler<SignUp>>()
    val signUpLiveData: LiveData<ResultHandler<SignUp>> = _signUpLiveData

    fun signUp(email: String, password: String) {
        _signUpLiveData.postValue(ResultHandler.Loading(true))
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = RetrofitService.retrofitService.signUp(
                    PostUser(email, password, true),
                    App.instance.APIKey()
                )
                val body = response.body()
                if(response.isSuccessful){
                    _signUpLiveData.postValue(ResultHandler.Success(body))
                }else{
                    _signUpLiveData.postValue(ResultHandler.Error(body,response.message()))
                }
            }
        }
        _signUpLiveData.postValue(ResultHandler.Loading(false))
    }
}