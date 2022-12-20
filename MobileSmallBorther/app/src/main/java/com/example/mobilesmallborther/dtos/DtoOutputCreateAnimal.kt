package com.example.mobilesmallbrother.dtos

data class DtoOutputCreateAnimal(val nameAnimal: String, val breed: String, val tag: Boolean?, val birthdate: String?,
                                 val descriptionAnimal: String, val height: String?, val gender: String, val typeAnimal: String,
                                 val statutAnimal: String, val urlImage: String?, val idClient: Int)