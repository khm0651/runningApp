package com.example.runningappvita.utils

import android.content.Context
import com.example.runningappvita.model.AccessToken
import com.example.runningappvita.model.RefreshToken

class UserAuth (context: Context) {


    private val mSharedPreferences = context.getSharedPreferences(MY_INFO, Context.MODE_PRIVATE)

    fun setFcmAccessToken(fcmAccessToken: String){
        val editor = mSharedPreferences.edit()
        editor.putString(FCM_TOKEN, fcmAccessToken)
        editor.apply()
    }

    fun setGoogleIdToken(googleIdToken: String, googleEmail: String){
        val editor = mSharedPreferences.edit()
        editor.putString(FCM_TOKEN, googleIdToken)
        editor.putString(GOOGLE_ID_ACCOUNT,googleEmail)
        editor.apply()
    }

    fun getCurrentToken(): String = mSharedPreferences.getString(ACCESS_TOKEN,"")!!

    fun getRefreshToken(): String = mSharedPreferences.getString(REFRESH_TOKEN,"")!!

    fun getGoogleIdToken(): String = mSharedPreferences.getString(GOOGLE_ID_TOKEN, "")!!

    fun saveAuthToken(accessToken: AccessToken, refreshToken: RefreshToken) {
        setCurrentToken(accessToken)
        setRefreshToken(refreshToken)
    }

    private fun setRefreshToken(token: RefreshToken) {
        val editor = mSharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, token.token)
        editor.putString(EXPIRED_AT, token.expiresAt)
        editor.apply()
    }

    private fun setCurrentToken(token: AccessToken) {
        val editor = mSharedPreferences.edit()
        editor.putString(REFRESH_TOKEN, token.token)
        editor.putString(REFRESH_TOKEN_EXPIRED_AT, token.expiresAt)
        editor.apply()
    }

    fun clearAuthToken() {
        val editor = mSharedPreferences.edit()
        editor.remove(REFRESH_TOKEN)
        editor.remove(REFRESH_TOKEN_EXPIRED_AT)
        editor.remove(ACCESS_TOKEN)
        editor.remove(EXPIRED_AT)
        editor.apply()
    }

    companion object{
        const val ACCESS_TOKEN: String = "access_token"
        const val MY_INFO = "my_info"
        const val FCM_TOKEN = "fcm_token "
        const val GOOGLE_ID_ACCOUNT = "google_id_account"
        const val REFRESH_TOKEN = "refresh_token "
        const val GOOGLE_ID_TOKEN = "google_id_token "
        const val EXPIRED_AT = "expired_at"
        const val REFRESH_TOKEN_EXPIRED_AT = "refresh_expired_at"

    }
}

