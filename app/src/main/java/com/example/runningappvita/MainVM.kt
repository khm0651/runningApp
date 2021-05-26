package com.example.runningappvita

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.runningappvita.utils.Event

class MainVM : ViewModel() {
    private val _navigateToDetails = MutableLiveData<Event<Boolean>>()

    val navigateToDetails : LiveData<Event<Boolean>>
        get() = _navigateToDetails

    fun userClicksOnButton(){
        _navigateToDetails.value = Event(true)
    }
}