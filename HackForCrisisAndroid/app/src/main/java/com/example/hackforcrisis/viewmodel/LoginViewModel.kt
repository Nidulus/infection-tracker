package com.example.hackforcrisis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import api.RetrofitProvider
import com.example.hackforcrisis.model.LoginRequest
import com.example.hackforcrisis.token.LOGIN_URL
import com.example.hackforcrisis.token.TokenHandler
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class LoginViewModel(private val tokenHandler: TokenHandler) : ViewModel() {

    fun login(loginRequest: LoginRequest): Flowable<Boolean> {
        return Flowable.create({ emitter ->
            RetrofitProvider().createRetrofit(LOGIN_URL)
                .flatMap { tokenHandler.getToken(it, loginRequest) }
                .doOnSuccess {
                    Log.d("Token", " received: $it")
                    emitter.onNext(true)
                }
                .doOnError {
                    Log.d("Token", "not received")
                    emitter.onNext(false)
                }
                .onErrorReturnItem("")
                .subscribe()
        }, BackpressureStrategy.LATEST)
    }


    private fun loginSuccess() {

    }

}