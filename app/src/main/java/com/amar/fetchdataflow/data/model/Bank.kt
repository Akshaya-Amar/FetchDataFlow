package com.amar.fetchdataflow.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
     @SerializedName("cardExpire") val cardExpire: String? = null,
     @SerializedName("cardNumber") val cardNumber: String? = null,
     @SerializedName("cardType") val cardType: String? = null,
     @SerializedName("currency") val currency: String? = null,
     @SerializedName("iban") val iban: String? = null
) : Parcelable