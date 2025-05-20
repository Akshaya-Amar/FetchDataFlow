package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Hair(
    @SerializedName("color") val color: String? = null,
    @SerializedName("type") val type: String? = null
)