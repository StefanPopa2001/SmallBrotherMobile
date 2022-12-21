package com.example.mobilesmallborther

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.ActivityLoginBinding
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: ClientManagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClientManagerViewModel::class.java)
        setContentView(binding.root)

        viewModel.mutableLiveDataLoginClient.observe(this) {
            if(viewModel.acceptLogin) {
                Toast.makeText(this, "Bienvenue " + it?.firstName + " !", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("dtoInputClient", it)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLoginActivityConnection.setOnClickListener {
            val clientLogin = DtoOutputLoginClient(
                binding.etLoginActivityMail.text.toString(), binding.etLoginActivityPassword.text.toString())

            viewModel.launchFetchByLogin(clientLogin)
        }
    }
}