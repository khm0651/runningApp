package com.example.runningappvita.model.request

import com.google.gson.annotations.SerializedName

data class GoogleLoginReq(
    @SerializedName("googleIdToken") val googleIdToken: String
){
    companion object{
        fun create(googleIdToken: String) = GoogleLoginReq(googleIdToken)
    }
}
