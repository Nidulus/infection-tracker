package com.example.hackforcrisis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    init {
    }

    fun login(user: String, pw: String) {
        Log.d("UserName: ", user)
        Log.d("pw: ", pw)
    }
}