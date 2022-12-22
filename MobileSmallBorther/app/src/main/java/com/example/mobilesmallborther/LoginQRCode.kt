package com.example.mobilesmallborther

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesmallborther.databinding.ActivityLoginBinding
import com.example.mobilesmallborther.databinding.ActivityLoginQrcodeBinding
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class LoginQRCode : AppCompatActivity() {

    lateinit var viewModel: ClientManagerViewModel
    lateinit var binding: ActivityLoginQrcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginQrcodeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClientManagerViewModel::class.java)
        setContentView(R.layout.activity_login_qrcode)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener {

            scanCode()

        }


    }

    private fun scanCode() {
        val options = ScanOptions()
        options.setPrompt("Volume up to flash on")
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        options.captureActivity = CaptureAct::class.java
        barLaucher.launch(options)
    }

    var barLaucher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents != null) {

            val mail = result.contents.split(",")

            //Mettre la co ici
            val data = DtoOutputLoginClient(mail = mail[0], hashedPassword = mail[1])

            viewModel.mutableLiveDataLoginClient.observe(this) {

                if(viewModel.acceptLogin)
                {
                    //Toast.makeText(this, "${mail[0]}${mail[1]}", Toast.LENGTH_LONG).show()

                    Toast.makeText(this, "Bienvenue " + it?.firstName + " !", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("dtoInputClient", it)
                    startActivity(intent)

                }
                else
                {
                    //Toast.makeText(this, "${mail[0]}${mail[1]}", Toast.LENGTH_LONG).show()

                    Toast.makeText(this, "Invalid login QR code", Toast.LENGTH_LONG).show()

                }

            }
            viewModel.launchFetchByLogin(data)


        }
    }


}