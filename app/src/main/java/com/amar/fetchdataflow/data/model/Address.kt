package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address") val address: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("stateCode") val stateCode: String? = null,
    @SerializedName("postalCode") val postalCode: String? = null,
    @SerializedName("coordinates") val coordinates: Coordinates? = null,
    @SerializedName("country") val country: String? = null
)