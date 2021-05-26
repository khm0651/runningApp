package com.example.runningappvita.model

data class Measurer(
    private val appVersion: String,
    private val birthYearAndMonth: Int,
    private val delDate: String,
    private val fcmToken: String,
    private val gender: String,
    private val height: Double,
    private val insDate: String,
    private val measurerCode: Int,
    private val famiwelMcode: String,
    private val nickname: String,
    private val retryCnt: Int,
    private val uid: String,
    private val userGrade: Int,
    private val userType: Int,
    private val pushYn: Int,
    private val missionPauseYn: Int? = null,
    private val missionRestartDate: String,
    private val missionPauseCheckYn: Int? = null,
    private val missionPauseDialogTn: Int,
    private val missionFinishDialogYn: Boolean = true
)
