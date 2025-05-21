package com.amar.fetchdataflow.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crypto(
     @SerializedName("coin") val coin: String? = null,
     @SerializedName("wallet") val wallet: String? = null,
     @SerializedName("network") val network: String? = null
) : Parcelable