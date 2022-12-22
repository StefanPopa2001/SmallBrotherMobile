package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilesmallborther.databinding.ActivityCreateBinding
import com.example.mobilesmallborther.databinding.ActivityMainBinding
import com.example.mobilesmallbrother.dtos.DtoInputClient

class CreateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateBinding
    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        //dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient
    }

}