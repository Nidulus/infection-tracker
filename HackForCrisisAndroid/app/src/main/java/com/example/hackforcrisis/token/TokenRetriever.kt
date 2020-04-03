package com.example.hackforcrisis.token

import com.example.hackforcrisis.model.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenRetriever {
    @FormUrlEncoded
    @POST(TOKEN)
    fun requestToken(
        @Body request: LoginRequest
    ): Call<TokenResponse>
}