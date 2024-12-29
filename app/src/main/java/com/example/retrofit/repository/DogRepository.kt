package com.example.retrofit.repository

import com.example.retrofit.DogResponse
import com.example.retrofit.network.DogRetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class DogRepository {
    private val api = DogRetrofitInstance.api

    suspend fun fetchRandomDog(): Result<DogResponse> {
        return try {
            val response = api.getImage()
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(Exception("HTTP error: ${e.code()} ${e.message()}"))
        } catch (e: IOException) {
            // Handle network errors
            Result.failure(Exception("Network error: ${e.message}"))
        } catch (e: Exception) {
            // Handle unexpected errors
            Result.failure(Exception("Unexpected error: ${e.message}"))
        }
    }
}