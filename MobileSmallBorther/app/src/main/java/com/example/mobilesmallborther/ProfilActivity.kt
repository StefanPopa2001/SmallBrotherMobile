package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.ActivityProfilBinding
import com.example.mobilesmallborther.databinding.FragmentProfilManagerBinding
import com.example.mobilesmallbrother.dtos.DtoInputClient
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient

class ProfilActivity : AppCompatActivity() {


    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient
    }

}
