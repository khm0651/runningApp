package com.example.runningappvita.retrofit

import com.example.runningappvita.VitaAsyncStorage
import com.example.runningappvita.model.AuthResponse
import com.example.runningappvita.model.AuthResult
import com.example.runningappvita.model.request.GoogleLoginReq
import com.example.runningappvita.retrofit.api.MeasurersAPI
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import javax.inject.Inject

class TokenInterceptor: Interceptor {

    @Inject
    lateinit var vitaAsyncStorage: VitaAsyncStorage

    override fun intercept(chain: Interceptor.Chain): Response {
        var request:Request = chain.request()
        val currentToken = vitaAsyncStorage.getUserCurrentToken()
        if(request.header(NAME_HEADER_TOKEN) != null
            && request.header(NAME_HEADER_TOKEN).isNullOrEmpty()
            && currentToken.isNotEmpty()
        ){
            request = request.newBuilder()
                .removeHeader(NAME_HEADER_TOKEN)
                .addHeader(NAME_HEADER_TOKEN, currentToken)
                .build()

            val response = chain.proceed(request)
            if(response.code() == 401){
                val refreshToken = vitaAsyncStorage.getUserRefreshToken()
                val googleIdToken = vitaAsyncStorage.getUserGoogleIdToken()
                val shouldLogoutUI = shouldReqHeaderKeepAppAlive(request)
                val authResult: AuthResult? = if(refreshToken.isNotEmpty()) refreshNewToken(currentToken,refreshToken,googleIdToken,shouldLogoutUI) else null
            }

        }
    }

    private fun refreshNewToken(currentToken: String, refreshToken: String, googleIdToken: String, shouldLogoutUI: Boolean): AuthResult? {
        var authResult: AuthResult? = null
        try {
            var response : retrofit2.Response<AuthResponse> =
                Service.createServiceNoneInterceptor(MeasurersAPI::class.java).refreshAccessToken(currentToken, refreshToken).execute()

            var count = 0
            var nextSuccess = true
            while(!response.isSuccessful){
                count++
                if(count > TIME_REPEAT_NEW_REFRESH_TOKEN){
                    nextSuccess = false
                    break
                }
                response = Service.createServiceNoneInterceptor(MeasurersAPI::class.java).createAccessTokenIfNeed(GoogleLoginReq.create(googleIdToken)).execute()

                if(!response.isSuccessful){
                    nextSuccess = false
                    break
                }
            }

            val code = response.code()
            if(nextSuccess){
                val data = response.body()
                authResult = data?.let { it.getSource() } ?: null
                authResult?.let { vitaAsyncStorage.getUserStore().saveAuthToken(authResult.accessToken, authResult.refreshToken) }
            } else{
                if( code == 401 || code == 404) vitaAsyncStorage.getUserStore().clearAuthToken()
//                if(shouldLogoutUI) pushActionLogout()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return authResult
    }

    private fun shouldReqHeaderKeepAppAlive(request: Request): Boolean {
        val value = request.header(NAME_HEADER_KEEP_APP_ALIVE)
        return !value.isNullOrEmpty() && value.toBoolean()
    }

    companion object{
        const val NAME_HEADER_TOKEN = "access-token"
        const val HEADER_NEED_TOKEN = "access-token: "
        const val NAME_HEADER_KEEP_APP_ALIVE = "keep-app-alive"
        const val TIME_REPEAT_NEW_REFRESH_TOKEN = 3
    }
}