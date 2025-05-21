package com.amar.fetchdataflow.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
     @SerializedName("id") val id: Int? = null,
     @SerializedName("firstName") val firstName: String? = null,
     @SerializedName("lastName") val lastName: String? = null,
     @SerializedName("maidenName") val maidenName: String? = null,
     @SerializedName("age") val age: Int? = null,
     @SerializedName("gender") val gender: String? = null,
     @SerializedName("email") val email: String? = null,
     @SerializedName("phone") val phone: String? = null,
     @SerializedName("username") val username: String? = null,
     @SerializedName("password") val password: String? = null,
     @SerializedName("birthDate") val birthDate: String? = null,
     @SerializedName("image") val image: String? = null,
     @SerializedName("bloodGroup") val bloodGroup: String? = null,
     @SerializedName("height") val height: Double? = null,
     @SerializedName("weight") val weight: Double? = null,
     @SerializedName("eyeColor") val eyeColor: String? = null,
     @SerializedName("hair") val hair: Hair? = null,
     @SerializedName("ip") val ip: String? = null,
     @SerializedName("address") val address: Address? = null,
     @SerializedName("macAddress") val macAddress: String? = null,
     @SerializedName("university") val university: String? = null,
     @SerializedName("bank") val bank: Bank? = null,
     @SerializedName("company") val company: Company? = null,
     @SerializedName("ein") val ein: String? = null,
     @SerializedName("ssn") val ssn: String? = null,
     @SerializedName("userAgent") val userAgent: String? = null,
     @SerializedName("crypto") val crypto: Crypto? = null,
     @SerializedName("role") val role: String? = null
) : Parcelable