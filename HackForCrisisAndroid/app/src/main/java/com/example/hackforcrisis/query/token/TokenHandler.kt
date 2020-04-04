package com.example.hackforcrisis.query.token

import android.util.Log
import com.example.hackforcrisis.model.LoginRequest
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception


class TokenHandler {
    fun getToken(retrofit: Retrofit, loginRequest: LoginRequest): Single<String> {
        return Single.create {
                emitter ->
            val tokenRetriever = retrofit.create(TokenRetriever::class.java)

            tokenRetriever.requestToken(loginRequest).enqueue(object: Callback<TokenResponse> {
                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    emitter.onError(t)
                }

                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    val accessToken = getAccessToken(response)
                    if (accessToken != null) {
                        emitter.onSuccess(accessToken)
                    } else {
                        emitter.onError(TokenException("Invalid token response"))
                    }

                }
            })
        }
    }

    private fun getAccessToken(response: Response<TokenResponse>): String? {
        Log.d("Response:" , response.message())
        return if (response.isSuccessful) {
            response.body()?.accessToken
        } else {
            null
        }
    }
}

class TokenException(msg: String): Exception(msg)