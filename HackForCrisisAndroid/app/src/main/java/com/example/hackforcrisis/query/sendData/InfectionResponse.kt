package com.example.hackforcrisis.query.sendData

import com.example.hackforcrisis.query.ACCESS_TOKEN
import com.example.hackforcrisis.query.STATUS_CODE
import com.google.gson.annotations.SerializedName

data class InfectionResponse(
    @SerializedName(ACCESS_TOKEN)
    val accessToken: String? = null,

    @SerializedName(STATUS_CODE)
    val statusCode: Int
)