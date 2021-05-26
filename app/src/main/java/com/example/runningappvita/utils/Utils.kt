package com.example.runningappvita.utils

object Utils {

    fun isEmpty(str: CharSequence?): Boolean = (str == null || str.isEmpty())

    fun isNotEmpty(str: CharSequence?): Boolean = (str != null && str.isNotEmpty())
}