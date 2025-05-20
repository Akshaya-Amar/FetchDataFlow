package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lng") val lng: Double? = null
)