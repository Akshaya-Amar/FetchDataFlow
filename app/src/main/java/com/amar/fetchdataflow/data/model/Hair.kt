package com.amar.fetchdataflow.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hair(
     @SerializedName("color") val color: String? = null,
     @SerializedName("type") val type: String? = null
) : Parcelable