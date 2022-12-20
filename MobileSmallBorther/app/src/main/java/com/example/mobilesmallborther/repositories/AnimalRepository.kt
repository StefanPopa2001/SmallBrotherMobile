package com.example.mobilesmallbrother.repositories

import com.example.mobilesmallbrother.dtos.DtoInputAnimal
import com.example.mobilesmallbrother.dtos.DtoOutputCreateAnimal
import retrofit2.http.*

interface AnimalRepository {

    @GET("animal/fetchByIdClient/{idClient}")
    suspend fun fetchByIdClient(@Path("idClient") idClient: Int): List<DtoInputAnimal>

    @POST("animal/create")
    suspend fun create(@Body dtoOutputCreateAnimal: DtoOutputCreateAnimal): DtoInputAnimal

    @PATCH("post/changeStatutDefault/{id}")
    suspend fun changeStatutDefault(@Path("id") id: Int): DtoInputAnimal

    @PATCH("post/changeStatutLost/{id}")
    suspend fun changeStatutLost(@Path("id") id: Int): DtoInputAnimal
}