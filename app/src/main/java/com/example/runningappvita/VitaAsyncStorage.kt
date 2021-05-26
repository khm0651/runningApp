package com.example.runningappvita


import com.example.runningappvita.utils.UserAuth
import com.example.runningappvita.utils.VitaSharePre


class VitaAsyncStorage (
     private val userAuth: UserAuth,
     private val vitaShare: VitaSharePre
) {

    fun getUserStore(): UserAuth = userAuth

    fun getUserCurrentToken(): String = userAuth.getCurrentToken()

    fun getUserRefreshToken(): String = userAuth.getRefreshToken()

    fun getUserGoogleIdToken(): String = userAuth.getGoogleIdToken()

}