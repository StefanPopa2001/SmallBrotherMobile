package com.example.mobilesmallborther

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.ActivityProfilBinding
import com.example.mobilesmallbrother.dtos.DtoInputClient

class ProfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilBinding
    lateinit var viewModel: AnimalManagerViewModel
    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AnimalManagerViewModel::class.java)
        setContentView(binding.root)

        dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient

        viewModel.mutableLiveDataByIdClientAnimal.observe(this) {
            Log.i("Test", it.toString())
        }

        viewModel.launchFetchByIdClient(dtoInputClient.idClient)
    }
}