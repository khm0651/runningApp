package com.example.runningappvita.model

import com.google.gson.annotations.SerializedName

data class AuthResult(
    @SerializedName("accessToken") val accessToken: AccessToken,
    @SerializedName("refreshToken") val refreshToken: RefreshToken,
    @SerializedName("measurer") val measurer: Measurer
)
