package com.example.retrofit.repository

import com.example.retrofit.CatResponse
import com.example.retrofit.network.CatRetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class CatRepository {
    private val api = CatRetrofitInstance.api

    suspend fun fetchRandomCat(): Result<CatResponse> {
        return try {
            val response = api.getImage()
            if (response.isNotEmpty()) {
                Result.success(response.first()) // Get the first item from the list
            } else {
                Result.failure(Exception("No images found"))
            }
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