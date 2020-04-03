package com.example.hackforcrisis.token

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName(ACCESS_TOKEN)
    val accessToken: String? = null,

    @SerializedName(STATUS_CODE)
    val statusCode: Int
)