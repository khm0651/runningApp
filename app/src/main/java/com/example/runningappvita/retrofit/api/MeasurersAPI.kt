package com.example.runningappvita.retrofit.api


import com.example.runningappvita.model.AuthResponse
import com.example.runningappvita.model.request.GoogleLoginReq
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MeasurersAPI {

    @POST(REFRESH_ACCESS_TOKEN)
    fun refreshAccessToken(
        @Header("access-token") accessToken: String,
        @Header("refresh-token") refreshToken: String
    ): Call<AuthResponse>

    @POST(CREATE_ACCESS_TOKEN)
    fun createAccessTokenIfNeed(@Body body: GoogleLoginReq): Call<AuthResponse>

    companion object{
        const val REFRESH_ACCESS_TOKEN = "/v1/access-tokens/refresh"
        const val CREATE_ACCESS_TOKEN = "/v1/access-tokens"
    }
}