package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.ActivityCreateBinding
import com.example.mobilesmallborther.databinding.ActivityMainBinding
import com.example.mobilesmallbrother.dtos.DtoInputClient
import com.example.mobilesmallbrother.dtos.DtoOutputCreateAnimal

class CreateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateBinding
    lateinit var viewModel: ProfilManagerViewModel
    lateinit var dtoInputClient: DtoInputClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProfilManagerViewModel::class.java)
        setContentView(binding.root)
        dtoInputClient = this.intent.getSerializableExtra("dtoInputClient") as DtoInputClient

        viewModel.mutableLiveDataCreateAnimal.observe(this) {
            Toast.makeText(this, "Animal ajouté !", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("dtoInputClient", dtoInputClient)
            startActivity(intent)
        }

        binding.btnFormCreateProfilFragmentSubmit.setOnClickListener {
            val genderIdSelected = binding.rgGenderCreate.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(genderIdSelected)

            if(binding.inputNameCreate.text.isNotEmpty() && binding.inputRaceCreate.text.isNotEmpty() && binding.inputDateCreate.text.isNotEmpty() &&
                    binding.inputDescriptionCreate.text.isNotEmpty() && binding.inputTypeCreate.text.isNotEmpty() && binding.inputUrlCreate.text.isNotEmpty()) {
                val animalCreate = DtoOutputCreateAnimal(binding.inputNameCreate.text.toString(), binding.inputRaceCreate.text.toString(),
                    binding.cbPuceCreate.isChecked, binding.inputDateCreate.text.toString(), binding.inputDescriptionCreate.text.toString(),
                    binding.spHeightCreate.selectedItem.toString(), radioButton.text.toString(), binding.inputTypeCreate.text.toString(), "N",
                    binding.inputUrlCreate.text.toString(), dtoInputClient.idClient)

                viewModel.launchCreate(animalCreate)
            }
            else {
                Toast.makeText(this, "Veuillez compléter tous les champs", Toast.LENGTH_LONG).show()
            }
        }
    }

}