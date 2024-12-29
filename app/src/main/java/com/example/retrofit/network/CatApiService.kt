package com.example.retrofit.network

import com.example.retrofit.CatResponse
import retrofit2.http.GET

interface CatApiService {
    @GET("search?limit=1")
    suspend fun getImage(): List<CatResponse>
}