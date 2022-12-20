package com.example.mobilesmallborther.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    // 10.0.2.2 pour utilisation sur émulateur et 5216 pour le port http de l'API
    private val baseUrl:String = "http://10.0.2.2:5216/api/v1/"

    fun newInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}