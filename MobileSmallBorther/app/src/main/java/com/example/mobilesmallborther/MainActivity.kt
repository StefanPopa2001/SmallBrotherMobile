package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilesmallborther.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableListeners()
    }
    private fun enableListeners() {
        // Evenement afin de passer d'une fenêtre à une autre
        binding.btnMainActivityFeed.setOnClickListener {
            val intent = Intent(this, InteractivityActivity::class.java)
            startActivity(intent)
        }

        binding.btnMainActivityGoToProfil.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        /*binding.btnMainActivityGoToImplicit.setOnClickListener {
            val intent = Intent(this, ImplicitActivity::class.java)
            startActivity(intent)
        }*/

    }
}
