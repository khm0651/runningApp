package com.example.runningappvita.utils

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import java.util.function.Consumer

object FirebaseMessagingHelper {

    private val TAG = FirebaseMessagingHelper::class.java.simpleName

    fun getFCMToken(callback: Consumer<String>){
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.d(TAG,"Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                val token = task.result
                Log.d(TAG, "Token : ${token}")
                callback.accept(token)

            }
    }
}