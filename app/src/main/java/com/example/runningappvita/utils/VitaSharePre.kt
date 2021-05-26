package com.example.runningappvita.utils

import android.content.Context
import android.content.SharedPreferences

class VitaSharePre(
    private val context: Context
) {
    private val VITA_APP = "VITA_APP"
    private val mSharedPreferences = context.getSharedPreferences(VITA_APP, Context.MODE_PRIVATE)

    fun getSharedPreferences(): SharedPreferences = mSharedPreferences


}