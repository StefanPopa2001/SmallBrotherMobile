package com.example.mobilesmallbrother.repositories

import com.example.mobilesmallbrother.dtos.DtoInputClient
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientRepository {

    @GET("client/fetchById")
    suspend fun fetchById(): DtoInputClient

    @POST("client/fetchByLogin")
    suspend fun fetchByLogin(@Body dtoOutputLoginClient: DtoOutputLoginClient): DtoInputClient
}