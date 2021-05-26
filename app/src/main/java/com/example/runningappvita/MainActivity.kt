package com.example.runningappvita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.runningappvita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(MainVM::class.java)

        binding.button.setOnClickListener {
            viewModel.userClicksOnButton()
        }

        Log.d("TAG","${viewModel.navigateToDetails.value}")
        viewModel.navigateToDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                startActivity(Intent(this,MainActivity2::class.java))
            }
        })
    }
}