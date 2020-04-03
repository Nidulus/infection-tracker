package com.example.hackforcrisis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import api.RetrofitProvider
import com.example.hackforcrisis.model.LoginRequest
import com.example.hackforcrisis.token.LOGIN_URL
import com.example.hackforcrisis.token.TokenHandler

class LoginViewModel(private val tokenHandler: TokenHandler) : ViewModel() {

    fun login(user: String, pw: String) {
        Log.d("UserName: ", user)
        Log.d("pw: ", pw)
        RetrofitProvider().createRetrofit(LOGIN_URL)
            .flatMap { tokenHandler.getToken(it, LoginRequest(user, pw)) }
            .doOnSuccess {
                Log.d("Token", " received: $it")
            }
            .doOnError {
                Log.d("Token", "not received")
            }
            .onErrorReturnItem("")
            .subscribe()
    }

}