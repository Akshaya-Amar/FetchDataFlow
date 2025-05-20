package com.amar.fetchdataflow.data.model


import com.google.gson.annotations.SerializedName

data class Company(
     @SerializedName("department") val department: String? = null,
     @SerializedName("name") val name: String? = null,
     @SerializedName("title") val title: String? = null,
     @SerializedName("address") val address: Address? = null
)