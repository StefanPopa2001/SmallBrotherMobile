package com.example.mobilesmallbrother.dtos

data class DtoInputClient(val idClient: Int, val firstName: String, val lastName: String, val gender: String, val mail: String,
                          val phoneNumber: String, val town: String, val roleClient: String, val urlImage: String?)
