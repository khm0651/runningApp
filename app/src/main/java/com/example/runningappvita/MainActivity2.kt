package com.example.runningappvita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.runningappvita.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMain2Binding = DataBindingUtil.setContentView(this,R.layout.activity_main2)
        val viewModel = ViewModelProvider(this).get(MainVM::class.java)

        Log.d("TAG","${viewModel.navigateToDetails.value}")

        viewModel.navigateToDetails.observe(this, Observer {

        })
    }
}