package com.example.runningappvita.model

import com.example.runningappvita.model.baseResponse.DataResponse
import com.google.gson.annotations.SerializedName

class AuthResponse(): BaseResponse<AuthResponse.Data, AuthResult>() {

    inner class Data(
        @SerializedName("accessToken") private val accessToken: AccessToken,
        @SerializedName("refreshToken") private val refreshToken: RefreshToken,
        @SerializedName("measurer") private val measurer: Measurer
    ): DataResponse<AuthResult>() {
        override fun getData(): AuthResult = AuthResult(accessToken, refreshToken, measurer)
    }

    override fun getSource(): AuthResult? {
        return this.data?.let {
            it.getData()
        } ?: null
    }

}
