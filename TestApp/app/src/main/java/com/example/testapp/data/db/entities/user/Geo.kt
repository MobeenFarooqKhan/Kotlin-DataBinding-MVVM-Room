package com.example.testapp.data.db.entities.user

import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat") val lat : Double,
    @SerializedName("lng") val lng : Double
)