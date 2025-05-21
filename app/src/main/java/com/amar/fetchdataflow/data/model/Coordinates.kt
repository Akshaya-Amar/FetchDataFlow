package com.amar.fetchdataflow.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
     @SerializedName("lat") val lat: Double? = null,
     @SerializedName("lng") val lng: Double? = null
) : Parcelable