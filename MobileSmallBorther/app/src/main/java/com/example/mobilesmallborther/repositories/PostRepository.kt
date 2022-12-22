package com.example.mobilesmallbrother.repositories

import com.example.mobilesmallbrother.dtos.DtoInputPost
import retrofit2.http.GET

interface PostRepository {

    @GET("post/fetchAllNotFound")
    suspend fun fetchAllNotFound(): List<DtoInputPost>
}