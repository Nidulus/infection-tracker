package com.example.hackforcrisis.model

import com.google.gson.annotations.SerializedName

data class InfectionData(
    @SerializedName("nose")
    var nose: Boolean,

    @SerializedName("throat")
    var throat: Boolean,

    @SerializedName("headache")
    var headache: Boolean,

    @SerializedName("nausea")
    var nausea: Boolean,

    @SerializedName("muscle")
    var muscle: Boolean
)