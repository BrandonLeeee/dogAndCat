package com.example.retrofit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.repository.CatRepository
import com.example.retrofit.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val dogRepository: DogRepository = DogRepository()
    private val catRepository: CatRepository = CatRepository()

    private val _image = MutableStateFlow<String?>(null)
    val image: StateFlow<String?> get() = _image

    var isLoading = mutableStateOf(false)


    fun fetchImage(apiType: String) {
        viewModelScope.launch {
            isLoading.value = true
            val result = when (apiType) {
                "DOG" -> dogRepository.fetchRandomDog()
                "CAT" -> catRepository.fetchRandomCat()
                else -> Result.failure(Exception("Invalid API Type"))
            }

            result.fold(
                onSuccess = { response ->
                    // Extract the image URL based on the type
                    val imageUrl = when (apiType) {
                        "DOG" -> (response as DogResponse).message
                        "CAT" -> (response as CatResponse).url
                        else -> null
                    }
                    _image.value = imageUrl
                },
                onFailure = { exception ->
                    _image.value = null
                    println("Error fetching data: ${exception.message}")
                }
            )
            isLoading.value = false
        }
    }
}