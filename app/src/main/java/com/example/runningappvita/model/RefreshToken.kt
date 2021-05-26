package com.example.runningappvita.model

import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("expiresAt") val expiresAt: String,
    @SerializedName("token") val token: String,
    @SerializedName("measurerCode") val measurerCode: Long
)
