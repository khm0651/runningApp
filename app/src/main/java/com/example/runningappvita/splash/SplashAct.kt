package com.example.runningappvita.splash;

import android.content.Intent
import android.os.Bundle;
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.runningappvita.login.LoginAct

class SplashAct : AppCompatActivity() {

    private lateinit var viewmodel : SplashVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(SplashVM::class.java)
        delayWith()
        setupObserver()
    }


    private fun setupObserver() {
        viewmodel.nextPageLogin.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                goActLogin()
            }
        })
    }

    private fun goActLogin() {
        val intent = Intent(this, LoginAct::class.java)
        startActivity(intent)
        finish()
    }

    private fun delayWith() {
        Handler(Looper.getMainLooper()).postDelayed({
            onSplashReady()
        }, TIME_DELAY_MILLIS)
    }

    private fun onSplashReady() {
        viewmodel.start()
    }

    companion object{
        const val TIME_DELAY_MILLIS = 1_500L
    }
}