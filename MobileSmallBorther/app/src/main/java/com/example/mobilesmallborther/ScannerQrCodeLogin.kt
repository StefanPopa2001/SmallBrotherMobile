package com.example.mobilesmallborther

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.mobilesmallborther.databinding.ActivityLoginBinding
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient

class ScannerQrCodeLogin : AppCompatActivity() {
    lateinit var viewModel: ClientManagerViewModel
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_qr_code_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClientManagerViewModel::class.java)

        if (ContextCompat.checkSelfPermission(
                this@ScannerQrCodeLogin,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@ScannerQrCodeLogin,
                arrayOf(Manifest.permission.CAMERA),
                123
            )
        } else {
            startScanning()
        }

    }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 123) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show()
                    startScanning()
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }

        lateinit var codeScanner: CodeScanner

        public fun startScanning() {
            // Parameters (default values)
            val scannerView: CodeScannerView = findViewById(R.id.scanner)
            codeScanner = CodeScanner(this, scannerView)
            codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
            codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
            // ex. listOf(BarcodeFormat.QR_CODE)
            codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
            codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
            codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
            codeScanner.isFlashEnabled = false // Whether to enable flash or not

            // Callbacks
            codeScanner.decodeCallback = DecodeCallback {
                runOnUiThread {

                    val mail = it.text.split(",")

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
            codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
                runOnUiThread {
                    Toast.makeText(this, "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG).show()
                }
            }

            scannerView.setOnClickListener {
                codeScanner.startPreview()
            }
        }


}