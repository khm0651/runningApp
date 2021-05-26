package com.example.runningappvita.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.runningappvita.utils.Event

class SplashVM: ViewModel() {

    private val _nextPageLogin = MutableLiveData<Event<Boolean>>()
    val nextPageLogin
        get() = _nextPageLogin

    fun start(){
        _nextPageLogin.value = Event(true)
    }
}