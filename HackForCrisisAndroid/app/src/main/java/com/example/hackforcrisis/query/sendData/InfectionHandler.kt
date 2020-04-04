package com.example.hackforcrisis.query.sendData

import com.example.hackforcrisis.model.InfectionData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception


class InfectionHandler {
    fun sendInfectionData(retrofit: Retrofit, infectionData: InfectionData): Single<String> {
        return Single.create {
                emitter ->
            val tokenRetriever = retrofit.create(InfectionRetriever::class.java)

            tokenRetriever.sendData(infectionData).enqueue(object: Callback<InfectionResponse> {
                override fun onFailure(call: Call<InfectionResponse>, t: Throwable) {
                    emitter.onError(t)
                }

                override fun onResponse(
                    call: Call<InfectionResponse>,
                    response: Response<InfectionResponse>
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

    private fun getAccessToken(response: Response<InfectionResponse>): String? {
        return if (response.isSuccessful) {
            response.body()?.accessToken
        } else {
            null
        }
    }
}

class TokenException(msg: String): Exception(msg)