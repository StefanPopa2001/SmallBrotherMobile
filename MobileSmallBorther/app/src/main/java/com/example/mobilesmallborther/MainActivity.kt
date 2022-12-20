package com.example.mobilesmallborther

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilesmallborther.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}