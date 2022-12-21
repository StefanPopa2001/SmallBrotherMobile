package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobilesmallborther.databinding.ActivityMainBinding
import com.example.mobilesmallbrother.dtos.DtoInputClient

class MainActivity() : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient
        enableListeners()
    }
    private fun enableListeners() {
        // Evenement afin de passer d'une fenêtre à une autre
        binding.btnMainActivityFeed.setOnClickListener {
            val intent = Intent(this, RequestsActivity::class.java)
            startActivity(intent)
        }

        binding.btnMainActivityGoToProfil.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            intent.putExtra("dtoInputClient", dtoInputClient)
            startActivity(intent)
        }

        /*binding.btnMainActivityGoToImplicit.setOnClickListener {
            val intent = Intent(this, ImplicitActivity::class.java)
            startActivity(intent)
        }*/

    }
}
