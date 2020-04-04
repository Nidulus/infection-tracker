package com.example.hackforcrisis.query.sendData

import com.example.hackforcrisis.model.InfectionData
import com.example.hackforcrisis.query.TOKEN
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InfectionRetriever {
    @FormUrlEncoded
    @POST(TOKEN)
    fun sendData(
        @Body request: InfectionData
    ): Call<InfectionResponse>
}