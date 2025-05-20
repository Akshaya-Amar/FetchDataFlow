package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("coin") val coin: String? = null,
    @SerializedName("wallet") val wallet: String? = null,
    @SerializedName("network") val network: String? = null
)