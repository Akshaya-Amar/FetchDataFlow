package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("cardExpire") val cardExpire: String? = null,
    @SerializedName("cardNumber") val cardNumber: String? = null,
    @SerializedName("cardType") val cardType: String? = null,
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("iban") val iban: String? = null
)