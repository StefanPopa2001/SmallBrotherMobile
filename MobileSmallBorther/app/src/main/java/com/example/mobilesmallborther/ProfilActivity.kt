package com.example.mobilesmallborther

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobilesmallbrother.dtos.DtoInputClient

class ProfilActivity : AppCompatActivity() {
    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient
        Log.i("test",dtoInputClient.toString())
    }
}