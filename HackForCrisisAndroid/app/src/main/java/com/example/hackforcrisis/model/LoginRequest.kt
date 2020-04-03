package com.example.hackforcrisis.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("user")
    var user: String,

    @SerializedName("password")
    var password: String
)