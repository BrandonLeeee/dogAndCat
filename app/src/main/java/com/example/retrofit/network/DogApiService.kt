package com.example.retrofit.network

import com.example.retrofit.DogResponse
import retrofit2.http.GET

interface DogApiService {
    @GET("random")
    suspend fun getImage(): DogResponse
}